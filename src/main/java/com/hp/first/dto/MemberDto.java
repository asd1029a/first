package com.hp.first.dto;

import com.hp.first.entity.Address;
import com.hp.first.entity.Role;
import lombok.Data;

import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class MemberDto {

    private String name;

    private String email;

    private String password;

    private String phoneNum;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private Role role;

}
