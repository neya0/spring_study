package com.example.springstudy.util;

import com.example.springstudy.entity.Member;

public interface DiscountPolicy {
    int discountPrice(Member member, int price);
}
