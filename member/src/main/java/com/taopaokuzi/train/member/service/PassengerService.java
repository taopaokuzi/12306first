package com.taopaokuzi.train.member.service;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.taopaokuzi.train.common.context.LoginMemberContext;
import com.taopaokuzi.train.common.util.SnowUtil;
import com.taopaokuzi.train.member.domain.Passenger;
import com.taopaokuzi.train.member.domain.PassengerExample;
import com.taopaokuzi.train.member.mapper.PassengerMapper;
import com.taopaokuzi.train.member.req.PassengerQueryReq;
import com.taopaokuzi.train.member.req.PassengerSaveReq;
import com.taopaokuzi.train.member.resp.PassengerQueryResp;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<PassengerQueryResp> queryList(PassengerQueryReq req){
        PassengerExample passengerExample= new PassengerExample();
        PassengerExample.Criteria criteria=passengerExample.createCriteria();//要把它放到外部，如果多个if的话，那么会以最后一个if里的create为准
        if(ObjectUtil.isNotNull(req.getMemberId())){
            criteria.andMemberIdEqualTo(req.getMemberId());
        }
        PageHelper.startPage(req.getPage(),req.getSize());//分页
        List<Passenger> passengerList= passengerMapper.selectByExample(passengerExample);
        return BeanUtil.copyToList(passengerList,PassengerQueryResp.class);
    }
}
