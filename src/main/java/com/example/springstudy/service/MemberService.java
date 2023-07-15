package com.example.springstudy.service;

import com.example.springstudy.entity.Member;

public interface MemberService {
    void joinMember(Member member);
    Member getMember(Long id);
}
