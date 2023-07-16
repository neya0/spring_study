package com.example.springstudy.repository.impl;

import com.example.springstudy.repository.MemberRepository;

import com.example.springstudy.entity.Member;
import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {

    /*
    동시 접근이 가능한 경우 동시성 이슈가 있을 수 있다. 따라서 이런 동시성 이슈를 막기 위해서
    ConcurrentMap<Long, Member> concurrentMap = new ConcurrentHashMap<>(); 을 사용한다.
    현재 사내 코드에서 이 concurrentMap 사용하는 경우는 동시다발적으로 접근을 하여 여러번의 로직 수행이 일어나면 안되는 경우.
    즉, lock 수행을 하기 위해서 사용하는 경우가 있다.
    */

    /* static -> 전역으로 사용되는 변수는 static 메모리에 포함되어 있어 모든 객체와 메소드의 영향이 동일하게 적용된다. */

    private static final Map<Long, Member> memberMap = new HashMap<>();

    @Override
    public void save(Member member) {
        memberMap.put(member.getId(), member);
    }

    @Override
    public Member findById(Long id) {
        return memberMap.get(id);
    }
}
