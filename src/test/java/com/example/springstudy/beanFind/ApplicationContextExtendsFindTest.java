package com.example.springstudy.beanFind;

import com.example.springstudy.util.DiscountPolicy;
import com.example.springstudy.util.FixDiscountPolicy;
import com.example.springstudy.util.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입 조회 시, 자식이 두 개 이상 있으면 중복 에러를 낸다.")
    void findBeanByParentTypeDuplication() {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입 조회 시, 자식이 두 개 이상 있으면 이름을 지정하면 된다.")
    void findBeanByParentTypeBeanName() {
        DiscountPolicy discountPolicy1 = ac.getBean("discountPolicy1", DiscountPolicy.class);
        assertThat(discountPolicy1).isInstanceOf(DiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회")
    void findAllBeanByParentType() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " / value = " + beansOfType.get(key));
        }
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회 - Object")
    void findAllBeanByParentTypeObject() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);

//        Map<String, Object> discountPolicyType = new HashMap<>();
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " / value = " + beansOfType.get(key));
//            if (beansOfType.get(key).getClass().getSuperclass().equals(DiscountPolicy.class)) {
//                discountPolicyType.put(key, beansOfType.get(key));
//            }
        }
//        assertThat(discountPolicyType.size()).isEqualTo(2);
    }

    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy discountPolicy1() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy discountPolicy2() {
            return new FixDiscountPolicy();
        }
    }
}
