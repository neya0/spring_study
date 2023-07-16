package com.example.springstudy;

import com.example.springstudy.config.AppConfig;
import com.example.springstudy.entity.Member;
import com.example.springstudy.entity.enums.Grade;
import com.example.springstudy.service.MemberService;
import com.example.springstudy.service.impl.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.joinMember(member);

        Member findMember = memberService.getMember(1L);

        System.out.println("member.getName() = " + member.getName());
        System.out.println("findMember.getName() = " + findMember.getName());
    }
}

