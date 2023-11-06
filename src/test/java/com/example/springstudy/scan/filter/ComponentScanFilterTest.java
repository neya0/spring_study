package com.example.springstudy.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentScanFilterTest {

    @Test
    void getFilter() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoComponentFilterConfig.class);

        BeanA beanA = ac.getBean("beanA", BeanA.class);
        Assertions.assertThat(beanA).isNotNull();

        org.junit.jupiter.api.Assertions.assertThrows(
                NoSuchBeanDefinitionException.class,
                () ->ac.getBean("beanB", BeanB.class)
        );
        /*
        스프링이 componentScan으로 빈을 등록할 때 클래스의 앞을 소문자로 바꿔서 등록함.
         */
    }

    @Configuration
    @ComponentScan(
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class AutoComponentFilterConfig {
        /*
        type의 경우 기본값이 FilterType.ANNOTATION이기 때문에 생략해도 좋다.
         */
    }
}
