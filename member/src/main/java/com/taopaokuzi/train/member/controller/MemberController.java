package com.taopaokuzi.train.member.controller;


import com.taopaokuzi.train.common.resp.CommonResp;
import com.taopaokuzi.train.member.req.MemberRegisterReq;
import com.taopaokuzi.train.member.service.MemberService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Resource
    private MemberService memberService;
    @GetMapping("/count")//进入到这的方法，都会拼上前面的/member
    public CommonResp <Integer>count() {
        int count= memberService.count();
        CommonResp<Integer> commonResp=new CommonResp<>();
        commonResp.setContent(count);
        return commonResp;
    }
    @PostMapping("/register")//进入到这的方法，都会拼上前面的/member
    public CommonResp <Long> register(@Valid MemberRegisterReq req) {
        long register = memberService.register(req);
        return new CommonResp<>(register);
    }
}