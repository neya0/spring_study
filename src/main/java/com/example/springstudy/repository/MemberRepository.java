package com.example.springstudy.repository;


import com.example.springstudy.entity.Member;

public interface MemberRepository {

    void save(Member member);
    Member findById(Long id);
}
