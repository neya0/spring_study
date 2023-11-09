package com.example.springstudy.service.impl;

import com.example.springstudy.entity.Member;
import com.example.springstudy.entity.Order;
import com.example.springstudy.repository.MemberRepository;
import com.example.springstudy.service.OrderService;
import com.example.springstudy.util.DiscountPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@RequiredArgsConstructor // 필수 필드 생성자를 만들어 준다.
public class OrderServiceImpl implements OrderService {

    /* 할인에 대한 것은 discountPolicy / 주문에 대한 것은 orderService. 이렇게 각 책임에 대한 분리로 단일책임원칙을 잘 지켜서 코딩을 한다. */
    /* 구현체가 확장되면 클라이언트까지 영향을 미친다. 이는 OCP 위반이다. */

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    private final Object object = new HashMap<>(); // final이 없거나 이미 값이 대입된 필드는 생성자시 required = false 상태가 된다.

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
/*
의존성 주입에는 4가지의 방법이 있다. 생성자 주입, setter주입(수정자 주입), 필드 주입, 일반 메소드 주입.

지금 코드에서 쓰인 것은 생성자 주입이다. 생성자를 통해서 OrderServiceImpl이 생성되는 시점에 의존성 주입이 이루어 진다.
생성자 주입은 아래에서 보듯이 final이 붙어 있다. 불변이고 필수값이다.

private final MemberRepository memberRepository;
private final DiscountPolicy discountPolicy;

@Autowired -> 생성자가 1개면 생략할 수 있음
public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    this.memberRepository = memberRepository;
    this.discountPolicy = discountPolicy;
}


수정자 주입은 스프링 빈이 생성되어서 스프링 콘텍스트에 등록된 후에 의존성 주입이 이루어 진다.
수정자 주입은 변경이 가능하고 선택해서 주입할 수 있다.

private MemberRepository memberRepository;
private DiscountPolicy discountPolicy;

@Autowired
public void setMemberRepository(MemberRepository memberRepository) { this.memberRepository = memberRepository;}
@Autowired
public void setDiscountPolicy(DiscountPolicy discountPolicy) { this.discountPolicy = discountPolicy;}

필드 주입은 간결하게 사용할 수 있지만 외부에서 접근이 어렵다. 따라서 테스트를 할 때 스프링 컨텍스트에 등록이 되어 있지 않다면 테스트가 어려워질 수 있다.
그래서 현재는 사용하지 않는다.

@Autowired private final MemberRepository memberRepository;
@Autowired private final DiscountPolicy discountPolicy;

일반 메소드 주입은 수정자 주입과 같다. 다만 이름이 좀 다를 뿐...

<우리는 생성자 주입을 사용해야한다.>
1. 불변 -> 대부분의 의존관계 주입은 불변한다.
2. 누락 -> 수정자나 다른 의존관계 주입 방법을 사용하게 되면 필수 의존 관계를 누락 시킬 수 있음. 생성자 주입을 사용하면 방지할 수 있다.
3. final 사용 -> final을 사용하면 셍성할 때 정해진 값이 변하지 않는다. 생성자에서만 값을 넣을 수 있다. 즉 제약을 만들어 준다.

이런 이유로 우리는 '생성자 주입'을 사용해야한다.
 */