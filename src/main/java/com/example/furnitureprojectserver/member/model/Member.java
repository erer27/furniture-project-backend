package com.example.furnitureprojectserver.member.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Member {

    public Member(String id, String password){
        this.id = id;
        this.password = password;
    }

    public Member() {
    }

    @Id
    private String id;

    @Column
    private String password;


}
