package com.example.springstudy.singletone;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SingletonAppConfig {

    public SingletonMemberService singletonMemberService() {
        return SingletonMemberService.getInstance();
    }
}
