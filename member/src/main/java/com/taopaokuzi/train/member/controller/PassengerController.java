package com.taopaokuzi.train.member.controller;


import com.taopaokuzi.train.common.resp.CommonResp;
import com.taopaokuzi.train.member.req.PassengerSaveReq;
import com.taopaokuzi.train.member.service.MemberService;
import com.taopaokuzi.train.member.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passenger")
public class PassengerController {
    @Resource
    private PassengerService passengerService;
    @PostMapping("/save")//进入到这的方法，都会拼上前面的/member
    public CommonResp <Object> register(@Valid @RequestBody PassengerSaveReq req) {
        passengerService.save(req);
        return new CommonResp<>();
    }
}