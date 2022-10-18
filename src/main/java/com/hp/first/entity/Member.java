package com.hp.first.entity;

import com.hp.first.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BasicEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String email;

    private String password;

    private String phoneNum;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Member(MemberDto memberDto) {
        this.name = memberDto.getName();
        this.email = memberDto.getEmail();
        this.password = memberDto.getPassword();
        this.phoneNum = memberDto.getPhoneNum();
        this.address = memberDto.getAddress();
        this.role = memberDto.getRole();
    }
}
