package com.example.springstudy.util;

import com.example.springstudy.entity.Member;
import com.example.springstudy.entity.enums.Grade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RateDiscountPolicyTest {

    @Test
    void discountVip() {
        DiscountPolicy discountPolicy = new RateDiscountPolicy();

        Member vip = new Member(1L, "vip", Grade.VIP);
        int discountPrice = discountPolicy.discountPrice(vip, 10000);

        Assertions.assertEquals(discountPrice, 1000);
    }

    @Test
    void discountBasic() {
        DiscountPolicy discountPolicy = new RateDiscountPolicy();

        Member basic = new Member(1L, "basic", Grade.BASIC);
        int discountPrice = discountPolicy.discountPrice(basic, 10000);

        Assertions.assertEquals(discountPrice, 0);
    }

}