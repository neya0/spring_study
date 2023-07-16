package com.example.springstudy.service.impl;

import com.example.springstudy.entity.Member;
import com.example.springstudy.entity.Order;
import com.example.springstudy.repository.MemberRepository;
import com.example.springstudy.repository.impl.MemoryMemberRepository;
import com.example.springstudy.service.OrderService;
import com.example.springstudy.util.DiscountPolicy;
import com.example.springstudy.util.FixDiscountPolicy;

public class OrderServiceImpl implements OrderService {

    /*
    할인에 대한 것은 discountPolicy / 주문에 대한 것은 orderService. 이렇게 각 책임에 대한 분리로 단일책임원칙을 잘 지켜서 코딩을 한다.
     */

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice, int count) {
        Member member = memberRepository.findById(memberId);
        int totalPrice = itemPrice * count;
        int discountPrice = discountPolicy.discountPrice(member, totalPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice, count);
    }
}
