package com.taopaokuzi.train.member.aspect;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;



@Aspect //切面
@Component //这样才可以被扫描到
public class LogAspect {

    private static final Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    public LogAspect() {
        System.out.println("LogAspect 被创建");
    }

    /**
     * 定义一个切点：拦截所有 controller 包下以 Controller 结尾的类的方法
     */
    @Pointcut("execution(public * com.taopaokuzi..*Controller.*(..))")//定义一个切点：拦截所有 controller 包下以 Controller 结尾的类的任意方法，任意参数
    public void controllerPointcut() {}

    /**
     * 方法执行前打印请求信息
     */
    @Before("controllerPointcut()")//前置通知，还没调用到方法之前，先执行before里的方法
    public void doBefore(JoinPoint joinPoint) {
        // 加入日志流水号,系统参数+随机生成
        MDC.put("LOG_ID", System.currentTimeMillis() + RandomUtil.randomString(3));

        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();//方法名

        // 打印请求内容
        LOG.info("------------- 请求开始 -------------");
        LOG.info("请求地址: {} {}", request.getRequestURL().toString(), request.getMethod());
        LOG.info("类名方法: {}.{}", signature.getDeclaringTypeName(), name);
        LOG.info("远程地址: {}", request.getRemoteAddr());

        // 打印请求参数
        Object[] args = joinPoint.getArgs();

        // 过滤掉 ServletRequest、ServletResponse、MultipartFile 等参数
        Object[] arguments = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest
                    || args[i] instanceof ServletResponse
                    || args[i] instanceof MultipartFile) {
                continue;
            }
            arguments[i] = args[i];
        }

        // 排除字段，敏感信息不打印，如手机号、邮箱、密码等，不能打印在日志里面
        String[] excludeProperties = {"password", "file", "mobile", "email"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludeFilter = filters.addFilter();
        excludeFilter.addExcludes(excludeProperties);

        LOG.info("请求参数: {}", JSONObject.toJSONString(arguments, excludeFilter));
    }

    /**
     * 方法执行后打印返回值和耗时
     */
    @Around("controllerPointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();

        // 排除字段，敏感信息不打印，如手机号、邮箱、密码等
        String[] excludeProperties = {"password", "file", "mobile", "email"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludeFilter = filters.addFilter();
        excludeFilter.addExcludes(excludeProperties);

        LOG.info("返回结果: {}", JSONObject.toJSONString(result, excludeFilter));
        LOG.info("------------- 请求结束 耗时: {} ms -------------", System.currentTimeMillis() - startTime);

        return result;
    }
}

