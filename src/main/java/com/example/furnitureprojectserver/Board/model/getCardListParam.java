package com.example.furnitureprojectserver.Board.model;

import com.example.furnitureprojectserver.member.model.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class getCardListParam {
    Member member;
    String keyword;
    Post post;
}
