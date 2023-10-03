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

        //이렇게 print 했을 때 내가 예상한 예시
       /*
        1. "AppConfig.memberService"
        2. "AppConfig.memberRepository"
        3. "AppConfig.orderService"
        4. "AppConfig.memberRepository"
        5. "AppConfig.discountPolicy"
         */

        // 실제 호출
        /*
        1. AppConfig.memberService
        2. AppConfig.memberRepository
        3. AppConfig.orderService
        4. AppConfig.discountPolicy
        */

        // memberRepository 한 번만 호출!!


        System.out.println("orderService.getMemberRepository() = " + orderService.getMemberRepository());
        System.out.println("memberService.getMemberRepository() = " + memberService.getMemberRepository());
        System.out.println("orderService.getClass() = " + orderService.getClass());

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(orderService.getMemberRepository());

    }

    @Test
    void buildConfiguration() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig appConfig = ac.getBean(AppConfig.class);
        System.out.println("appConfig.getClass() = " + appConfig.getClass());

        /*
        이유는 @configuration 떄문~! appConfig class를 출력해보면
        appConfig.getClass() = class com.example.springstudy.config.AppConfig$$EnhancerBySpringCGLIB$$85945de5
        이렇게 이상한 형태로 출력된다. 위에서 orderService class를 출력했을 떄와 다른 모양새 이다.
        (orderService.getClass() = class com.example.springstudy.service.impl.OrderServiceImpl)

        이런 이유는 @Configuration이 붙으면 CGLIB를 통해서 설정 코드를 변환해서 인스턴스로 저장하고 그 안의 @Bean이 붙은 메소드에 싱글톤을 적용시키기 때문이다.
        만일 @Configuration을 붙이지 않으면 스프링에서 관리하지 않게 되고 그렇게 되면 모든 로직이 자바 코드 그대로 실행되어서 싱글톤을 유지할 수 없게 된다.

        @Configuration을 붙이지 않고도 싱글톤을 유지하는 법은 @Autowire를 붙여서 의존성을 주입시키는 방법이 있는데 굳이 그렇게 하는 것보다는
        모든 설정파일에 @Configuration을 붙이는 편이 낫다.

        그럼 @Service, @Repository 등의 어노테이션도 그런 방식으로 스프링에서 관리하게 되는 걸까?

        먼저 이야기 하면 @Service, @Repository, @Controller 모두 @Component 어노테이션을 메타 어노테이션으로 가지고 있어서
        component scan 방식으로 등록이되기 때문에 싱글톤이 보장된다.

        + 스프링 컨테이너에는 서블릿용 컨테이너, 루트 컨테이너가 있는데
        루트 컨테이너는 모든 어플리케이션에서 사용하는 컨테이너이고, 서블릿용 컨테이너는 각각의 서블릿이 사용하는 컨테이너이다.
        @Service, @Repository, @Controller 어노테이션의 경우 각각의 용도에 맞춰 서블릿용 컨테이너 혹은 루트 컨테이너에 빈을 생성 된다.

        */

    }
}
