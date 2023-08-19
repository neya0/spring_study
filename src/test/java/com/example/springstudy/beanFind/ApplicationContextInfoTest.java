package com.example.springstudy.beanFind;

import com.example.springstudy.config.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 조회")
    void findAllBean() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = applicationContext.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " / object = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 조회")
    void findAppAllBean() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = applicationContext.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = applicationContext.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " / object = " + bean);
            }
        }
        /*
        결과 -> 아래처럼 애플리케이션 내에 사용자가 정의한 빈을 볼 수 있다.

        name = appConfig / object = com.example.springstudy.config.AppConfig$$EnhancerBySpringCGLIB$$336ddc7c@50b8ae8d
        name = memberService / object = com.example.springstudy.service.impl.MemberServiceImpl@255990cc
        name = orderService / object = com.example.springstudy.service.impl.OrderServiceImpl@51c929ae
        name = memberRepository / object = com.example.springstudy.repository.impl.MemoryMemberRepository@3c8bdd5b
        name = discountPolicy / object = com.example.springstudy.util.RateDiscountPolicy@29d2d081
         */
    }
}
