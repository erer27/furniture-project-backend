package com.example.furnitureprojectserver.member.service.impl;

import com.example.furnitureprojectserver.member.model.Member;
import com.example.furnitureprojectserver.member.repository.MemberRepository;
import com.example.furnitureprojectserver.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    @Override
    public Member signUpMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member loginMember(Member member) {
        Optional<Member> optionalMember = memberRepository.findById(member.getId());
        if(optionalMember.isPresent()){
            if(optionalMember.get().getPassword().equals(member.getPassword())){
                return optionalMember.get();
            }
        }


        return new Member("wrong member","wrong member");
    }

    @Override
    public boolean idDuplicationCheck(String id) {
        return memberRepository.existsById(id);
    }
}
