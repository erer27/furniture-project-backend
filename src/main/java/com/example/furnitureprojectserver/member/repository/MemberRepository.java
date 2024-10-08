package com.example.furnitureprojectserver.member.repository;

import com.example.furnitureprojectserver.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
