package com.example.furnitureprojectserver.member.controller;


import com.example.furnitureprojectserver.member.model.Member;
import com.example.furnitureprojectserver.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @RequestMapping(value="/idDuplicationCheck", method = {RequestMethod.GET, RequestMethod.POST})
    public boolean idDuplicationCheck(@RequestBody String id){
        System.out.println(id);
        return memberService.idDuplicationCheck(id);
    }

    @RequestMapping(value="/signup", method = {RequestMethod.GET, RequestMethod.POST})
    public Member signupMember(@RequestBody Member member){
        return memberService.signUpMember(member);
    }

    @RequestMapping(value="/login", method = {RequestMethod.GET, RequestMethod.POST})
    public Member loginMember(@RequestBody Member member){
        return memberService.loginMember(member);
    }


}
