package com.example.springstudy.service.impl;

import com.example.springstudy.repository.impl.MemoryMemberRepository;
import com.example.springstudy.util.RateDiscountPolicy;
import org.junit.jupiter.api.Test;

class OrderServiceImplTest {
    @Test
    void createOrder() {
        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new RateDiscountPolicy());
    }

}