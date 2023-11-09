package com.example.springstudy.autowired;

import com.example.springstudy.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {
    @Test
    void autowiredTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(NoBean.class);
    }

    static class NoBean {
        @Autowired(required = false) // 만약 스프링 빈이 없으면 의존성 주입을 패스한다.
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired //noBean2 = null
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired //noBean3 = Optional.empty
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }
}

