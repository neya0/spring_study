package com.example.springstudy.service.impl;

import com.example.springstudy.entity.Member;
import com.example.springstudy.repository.MemberRepository;
import com.example.springstudy.service.MemberService;

public class MemberServiceImpl implements MemberService {

    // 현재의 문제점 -> memberRepository 구현제까지 의존되고 있다. 이것은 DIP를 위반하고 있다.
    // 실제 구현체에도 의존하게 되면 유지보수가 어려워질 것 같다. 바뀔 때마다 모든 곳에 찾아가서 바꿔야 할테니까..이걸 어떻게 해결할 수 있을까?

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void joinMember(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member getMember(Long id) {
        return memberRepository.findById(id);
    }
}
