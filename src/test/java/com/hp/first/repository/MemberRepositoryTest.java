package com.hp.first.repository;

import com.hp.first.dto.MemberDto;
import com.hp.first.entity.Address;
import com.hp.first.entity.Member;
import com.hp.first.entity.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public void createMember() {
        MemberDto memberDto = new MemberDto();

        for (int i = 0; i < 10; i++) {
            memberDto.setName("테스트");
            memberDto.setAddress(new Address("1","길","도시"));
            memberDto.setEmail("test"+i+"@naver.com");
            memberDto.setPassword("1234");
            memberDto.setPhoneNum("01044440220");
            Member member = Member.createMember(memberDto,passwordEncoder);
            member.setRole(Role.ROLE_ADMIN);
            memberRepository.save(member);
        }
    }

    @Test
    public void 멤버저장() {
        createMember();

        List<Member> memberList = memberRepository.findAll();
        int memberSize = memberList.size();

        Assertions.assertThat(memberSize).isEqualTo(10);
    }


}