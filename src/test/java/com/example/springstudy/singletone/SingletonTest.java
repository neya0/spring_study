package com.example.springstudy.singletone;

import com.example.springstudy.config.AppConfig;
import com.example.springstudy.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig ac = new AppConfig();
        // 1.조회: 호출할 때마다 객체 생성
        MemberService memberService1 = ac.memberService();

        // 2.조회: 호출할 때마다 객체 생성
        MemberService memberService2 = ac.memberService();

        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 조회")
    void singletonPatternTest() {
        SingletonService singletonService1 = SingletonService.getSingleton();
        SingletonService singletonService2 = SingletonService.getSingleton();

        assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void singletonContainer() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        // 1.조회: 호출할 때마다 객체 생성
        MemberService memberService1 = ac.getBean(MemberService.class);

        // 2.조회: 호출할 때마다 객체 생성
        MemberService memberService2 = ac.getBean(MemberService.class);

        assertThat(memberService1).isSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 DI 컨테이너")
    void singletonPatternContainer(){
        SingletonAppConfig ac = new SingletonAppConfig();
        // 1.조회: 호출할 때마다 객체 생성
        SingletonMemberService memberService1 = ac.singletonMemberService();

        // 2.조회: 호출할 때마다 객체 생성
        SingletonMemberService memberService2 = ac.singletonMemberService();

        assertThat(memberService1).isSameAs(memberService2);
    }
}
