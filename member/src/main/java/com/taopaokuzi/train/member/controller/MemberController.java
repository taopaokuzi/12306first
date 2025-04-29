package com.taopaokuzi.train.member.controller;


import com.taopaokuzi.train.member.service.MemberService;
import jakarta.annotation.Resource;
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
    public Integer count() {

        return memberService.count();
    }
    @PostMapping("/register")//进入到这的方法，都会拼上前面的/member
    public long register(String mobile) {
        return memberService.register(mobile);
    }
}