package com.example.springstudy.service.impl;

import com.example.springstudy.entity.Member;
import com.example.springstudy.entity.Order;
import com.example.springstudy.repository.MemberRepository;
import com.example.springstudy.service.OrderService;
import com.example.springstudy.util.DiscountPolicy;

public class OrderServiceImpl implements OrderService {

    /* 할인에 대한 것은 discountPolicy / 주문에 대한 것은 orderService. 이렇게 각 책임에 대한 분리로 단일책임원칙을 잘 지켜서 코딩을 한다. */
    /* 구현체가 확장되면 클라이언트까지 영향을 미친다. 이는 OCP 위반이다. */

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice, int count) {
        Member member = memberRepository.findById(memberId);
        int totalPrice = itemPrice * count;
        int discountPrice = discountPolicy.discountPrice(member, totalPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice, count);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
