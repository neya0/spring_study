package com.example.springstudy.util;

import com.example.springstudy.entity.Member;
import com.example.springstudy.entity.enums.Grade;

public class FixDiscountPolicy implements DiscountPolicy{

    private int FixDiscountPolicyAmount = 1000;

    @Override
    public int discountPrice(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return FixDiscountPolicyAmount;
        }
        return 0;
    }
}
