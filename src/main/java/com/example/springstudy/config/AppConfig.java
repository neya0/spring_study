package com.example.springstudy.config;

import com.example.springstudy.repository.impl.MemoryMemberRepository;
import com.example.springstudy.service.MemberService;
import com.example.springstudy.service.OrderService;
import com.example.springstudy.service.impl.MemberServiceImpl;
import com.example.springstudy.service.impl.OrderServiceImpl;
import com.example.springstudy.util.DiscountPolicy;
import com.example.springstudy.util.RateDiscountPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 이렇게 의존성 주입을 맡아서 해주는 것을 DI 컨테이너라고 한다.
 */
@Configuration
public class AppConfig {

    /*
    app config -> 오로지 의존성 주입을 하는 역할을 한다. 이로써 service 구현을 할 때 직접적으로 구현체에 의존하지 않고 오로지 서비스 로직을 실행하는 것에만 집중할 수 있게 된다.
    !! 관심사의 분리 !! 어떤 구현체를 사용할지 정하는 역할은 app config, 실행하여 결과를 내는 것은 service.
    역할이 명확하게 보이는게 좋음

    빈이 상속관계를 가지고 있다면 부모를 조회하면 자식도 다 조회된다. 즉 하위로 있는 모든게 다 자동으로 조회된다는 것이다.
     */

    @Bean
    public MemberService memberService() {
        System.out.println("AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("AppConfig.orderService");
//        return new OrderServiceImpl(memberRepository(), discountPolicy());
        return null;
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        System.out.println("AppConfig.discountPolicy");
        return new RateDiscountPolicy();
    }
}
