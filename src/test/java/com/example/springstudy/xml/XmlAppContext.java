package com.example.springstudy.xml;

import com.example.springstudy.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class XmlAppContext {

    /*
    ApplicationContext는 Bean을 관리하는 것을 제외하고도 많은 부가기능들을 상속받아있다.

    EnvironmentCapable, ListableBeanFactory, HierarchicalBeanFactory, MessageSource, ApplicationEventPublisher, ResourcePatternResolver
    이 기능들 중 최근에 sse를 구현하면서 @ApplicationEventListener가 붙은 메소드는 따로 의존성 주입을 하지 않아도
    ApplicationEventPublisher를 통해서 의존성 주입이 자동으로 이루어진다는 부분을 알게 되었는데
    여기서 만나게 되서 매우 반갑다.

    ApplicationContext를 상속받은 많은 구현체 중에 우리는 요즘 자바 코드로 된 설정 정보로 빈을 등록, 관리하는 AnnotationConfigApplicationContext를 사용했었다.
    이 외에도 xml로 설정 정보로 빈을 등록, 관리하는 GenericXmlApplicationContext 도 있다.
     */
    @Test
    void xmlAppContext() {
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
