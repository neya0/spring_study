package com.example.springstudy.beanFind;

import com.example.springstudy.config.AppConfig;
import com.example.springstudy.service.MemberService;
import com.example.springstudy.service.impl.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


public class ApplicationContextBasicFindTest {
     AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

     @Test
    @DisplayName("빈 이름 조회")
    void findBeanByName() {
         MemberService memberService = ac.getBean("MemberService", MemberService.class);
         assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
     }

    @Test
    @DisplayName("빈 이름 조회 X")
    void findBeanByNameX() {
         /*
         ac.getBean("XXX", MemberService.class);
         이걸 실행하게 되면 "XXX"라는 이름의 bean이 없기 때문에 NoSuchBeanDefinitionException 에러가 난다.
         이 경우의 테스트! 아래처럼 하면 된다.
          */
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("XXX", MemberService.class));

    }
}
