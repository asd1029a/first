package com.hp.first.dto;

import com.hp.first.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    private String name;

    private String email;

    private String password;

    private String phoneNum;

    @Embedded
    private Address address;


}
