package com.example.furnitureprojectserver.member.service;

import com.example.furnitureprojectserver.member.model.Member;

public interface MemberService {

    Member signUpMember(Member member);

    Member loginMember(Member member);
    boolean idDuplicationCheck(String id);
}
