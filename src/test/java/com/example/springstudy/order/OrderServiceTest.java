package com.example.springstudy.order;

import com.example.springstudy.config.AppConfig;
import com.example.springstudy.entity.Member;
import com.example.springstudy.entity.Order;
import com.example.springstudy.entity.enums.Grade;
import com.example.springstudy.service.MemberService;
import com.example.springstudy.service.OrderService;
import com.example.springstudy.service.impl.MemberServiceImpl;
import com.example.springstudy.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    AppConfig appConfig = new AppConfig();
    MemberService memberService = appConfig.memberService();
    OrderService orderService = appConfig.orderService();

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.joinMember(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000, 1);

        Assertions.assertEquals(order.getDiscountPrice(), 1000);
    }
}
