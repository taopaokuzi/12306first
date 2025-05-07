package com.taopaokuzi.train.member.controller;


import com.taopaokuzi.train.common.resp.CommonResp;
import com.taopaokuzi.train.member.req.MemberLoginReq;
import com.taopaokuzi.train.member.req.MemberRegisterReq;
import com.taopaokuzi.train.member.req.MemberSendCodeReq;
import com.taopaokuzi.train.member.resp.MemberLoginResp;
import com.taopaokuzi.train.member.service.MemberService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/send-code")//进入到这的方法，都会拼上前面的/member
    public CommonResp <Long> sendCode(@Valid @RequestBody MemberSendCodeReq req) {
        memberService.sendCode(req);
        return new CommonResp<>();
    }
    @PostMapping("/login")//进入到这的方法，都会拼上前面的/member
    public CommonResp <MemberLoginResp> login(@Valid @RequestBody MemberLoginReq req) {
        MemberLoginResp resp = memberService.login(req);
        return new CommonResp<MemberLoginResp>(resp);
    }
}