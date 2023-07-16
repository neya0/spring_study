package com.example.springstudy.util;

import com.example.springstudy.entity.Member;
import com.example.springstudy.entity.enums.Grade;

public class RateDiscountPolicy implements DiscountPolicy{

    private int DiscountRate = 10;
    @Override
    public int discountPrice(Member member, int price) {
        if(member.getGrade().equals(Grade.VIP)) {
            return price * DiscountRate / 100;
        }
        return 0;
    }
}
