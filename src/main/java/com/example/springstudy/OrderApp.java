package com.example.springstudy;

import com.example.springstudy.config.AppConfig;
import com.example.springstudy.entity.Member;
import com.example.springstudy.entity.Order;
import com.example.springstudy.entity.enums.Grade;
import com.example.springstudy.service.MemberService;
import com.example.springstudy.service.OrderService;
import com.example.springstudy.service.impl.MemberServiceImpl;
import com.example.springstudy.service.impl.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        /*
        ApplicationContext -> 스프링 컨테이너 : 역할이 뭐지?
        @Configuration이 붙은 AppConfig를 기준으로 @Bean이 붙은 메소드를 메소드 명으로 스프링 컨테이너에 등록해준다.

        스프링 컨테이너가 어떻게 생성되는지
        new AnnotationConfigApplicationContext()를 통해서 스프링 컨테이너를 만든다.
        스프링 컨테이너가 생성되면 그 안에 빈 저장소가 생긴다.
        * 빈의 이름은 단순하고 명확하게 만들고 모두 다르게 만들어야 한다.
        스프링 컨테이너가 생성되고 스프링 빈이 생성되고 나면 설정관계를 확인하고 해당하는 의존성 주입을 한다.
         */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.joinMember(member);

        Order order = orderService.createOrder(memberId, "itemA", 20000, 1);
        System.out.println("order = " + order);
    }
}
