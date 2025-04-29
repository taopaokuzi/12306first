package com.taopaokuzi.train.member.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import com.taopaokuzi.train.common.exception.BusinessException;
import com.taopaokuzi.train.common.exception.BusinessExceptionEnum;
import com.taopaokuzi.train.common.util.SnowUtil;
import com.taopaokuzi.train.member.domain.Member;
import com.taopaokuzi.train.member.domain.MemberExample;
import com.taopaokuzi.train.member.mapper.MemberMapper;
import com.taopaokuzi.train.member.req.MemberRegisterReq;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Resource
    private MemberMapper memberMapper;
    public int count(){
        return Math.toIntExact(memberMapper.countByExample(null));
    }


    public long register(MemberRegisterReq req){
        String mobile=req.getMobile();
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);

        if(CollUtil.isNotEmpty(list)){
           // return list.get(0).getId();
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }
        Member member = new Member();
        member.setId(SnowUtil.getSnowflakeNextId());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }
}
