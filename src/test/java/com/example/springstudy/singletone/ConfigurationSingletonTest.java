package com.example.springstudy.singletone;

import com.example.springstudy.config.AppConfig;
import com.example.springstudy.service.impl.MemberServiceImpl;
import com.example.springstudy.service.impl.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {
    @Test
    void memberRepositoryTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

        System.out.println("orderService.getMemberRepository() = " + orderService.getMemberRepository());
        System.out.println("memberService.getMemberRepository() = " + memberService.getMemberRepository());

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(orderService.getMemberRepository());

    }
}
