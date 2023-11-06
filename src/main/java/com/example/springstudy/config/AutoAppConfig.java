package com.example.springstudy.config;

import com.example.springstudy.repository.MemberRepository;
import com.example.springstudy.repository.impl.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class),
        basePackages = "com.example.springstudy")
public class AutoAppConfig {

    @Bean(name = "memoryMemberRepository")
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}

/*
componentScan의 기본값은 componentScan 어노테이션이 붙은 클래스의 패키지를 기본값으로 한다.따라서 패키지의 가장 상위에 위치하는 것이 좋다.
 */

 /*
만일 동일한 이름의 빈이 자동으로 등록 된다면
BeanDefinitionStoreException: Failed to parse configuration class [com.example.springstudy.config.AutoAppConfig];
nested exception is org.springframework.context.annotation.ConflictingBeanDefinitionException:
Annotation-specified bean name 'service' for bean class [com.example.springstudy.service.impl.OrderServiceImpl] conflicts with existing,
non-compatible bean definition of same name and class [com.example.springstudy.service.impl.MemberServiceImpl]

이런 에러가 발생된다.

하지만 수종으로 빈을 생성했는데 자동 생성된 빈과 이름이 같다면?
DefaultListableBeanFactory - Overriding bean definition for bean 'memoryMemberRepository' with a different definition:
replacing [Generic bean: class [com.example.springstudy.repository.impl.MemoryMemberRepository];

이렇게 수동 빈으로 오버라이딩을 한다. 하지만 이런 경우가 개발자의 의도가 아닌 실수인 경우가 많다 보니 스프링 부트에서는

Description:

The bean 'memoryMemberRepository', defined in class path resource [com/example/springstudy/config/AutoAppConfig.class],
could not be registered. A bean with that name has already been defined in file
[/Users/gangjiseong/Desktop/spring-study/build/classes/java/main/com/example/springstudy/repository/impl/MemoryMemberRepository.class]
and overriding is disabled.

Action:

Consider renaming one of the beans or enabling overriding by setting spring.main.allow-bean-definition-overriding=true

spring.main.allow-bean-definition-overriding 설정을 false로 기본값을 갖기로 했다. 따라서 스프링에서는 자동 오버라이딩을 하지만 스프링 부트를 사용한다면 에러를 내고
spring.main.allow-bean-definition-overriding=true 이렇게 설정을 true로 변경하라고 안내한다.
 */
