package com.example.springstudy.member;

import com.example.springstudy.entity.Member;
import com.example.springstudy.entity.enums.Grade;
import com.example.springstudy.service.MemberService;
import com.example.springstudy.service.impl.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when
        memberService.joinMember(member);
        Member findMember = memberService.getMember(1L);

        // then
        Assertions.assertEquals(member, findMember);
    }
}
