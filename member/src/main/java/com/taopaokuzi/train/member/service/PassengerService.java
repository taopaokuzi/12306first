package com.taopaokuzi.train.member.service;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.taopaokuzi.train.common.context.LoginMemberContext;
import com.taopaokuzi.train.common.util.SnowUtil;
import com.taopaokuzi.train.member.domain.Passenger;
import com.taopaokuzi.train.member.mapper.PassengerMapper;
import com.taopaokuzi.train.member.req.PassengerSaveReq;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
@Service
public class PassengerService {
    @Resource
    private PassengerMapper passengerMapper;
    public void save(PassengerSaveReq req){
        DateTime now= DateTime.now();
        Passenger passenger= BeanUtil.copyProperties(req,Passenger.class);
        passenger.setMemberId(LoginMemberContext.getId());
        passenger.setId(SnowUtil.getSnowflakeNextId());
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        passengerMapper.insert(passenger);
    }
}
