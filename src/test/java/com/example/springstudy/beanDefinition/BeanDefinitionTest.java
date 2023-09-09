package com.example.springstudy.beanDefinition;

import com.example.springstudy.config.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.BeanDefinitionDsl;

public class BeanDefinitionTest {

    /*
    ApplicationContext로 설정하지 않은 것은 ApplicationContext의 구현체가 가지고 있는 BeanDefinitionReader를 통해서 설정 정보를 읽고
    BeanDefinition을 생성하고 그걸 스프링 컨테이너가 BeanDefinition을 통해서 스프링 빈 매타정보를 가지게 되기 때문이다.
     */
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 메타정보 확인")
    void findBeanDefinition() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + beanDefinitionName +
                        " / beanDefinition = " + beanDefinition);
            }
        }
    }
}
