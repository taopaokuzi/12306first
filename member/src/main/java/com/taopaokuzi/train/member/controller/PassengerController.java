package com.taopaokuzi.train.member.controller;


import com.taopaokuzi.train.common.context.LoginMemberContext;
import com.taopaokuzi.train.common.resp.CommonResp;
import com.taopaokuzi.train.member.domain.Passenger;
import com.taopaokuzi.train.member.req.PassengerQueryReq;
import com.taopaokuzi.train.member.req.PassengerSaveReq;
import com.taopaokuzi.train.member.resp.PageResp;
import com.taopaokuzi.train.member.resp.PassengerQueryResp;
import com.taopaokuzi.train.member.service.MemberService;
import com.taopaokuzi.train.member.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passenger")
public class PassengerController {
    @Resource
    private PassengerService passengerService;
    @PostMapping("/save")//进入到这的方法，都会拼上前面的/member
    public CommonResp <Object> register(@Valid @RequestBody PassengerSaveReq req) {
        passengerService.save(req);//保存不需要返回值
        return new CommonResp<>();
    }
    @GetMapping("/query-list")//进入到这的方法，都会拼上前面的/member
    public CommonResp <PageResp<PassengerQueryResp>> queryList(@Valid  PassengerQueryReq req) {
        req.setMemberId(LoginMemberContext.getId());
        PageResp<PassengerQueryResp> list= passengerService.queryList(req);//查询需要返回值
        return new CommonResp<>(list);
    }
    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id){
        passengerService.delete(id);
        return new CommonResp<>();
    }
}