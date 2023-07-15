package com.example.springstudy.repository;

import java.lang.reflect.Member;

public interface MemberRepository {

    void save(Member member);
    Member findById(Long id);
}
