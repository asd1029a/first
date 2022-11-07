package com.hp.first.repository;

import com.hp.first.dto.MemberDto;
import com.hp.first.entity.Address;
import com.hp.first.entity.Member;
import com.hp.first.status.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class OrderRepositoryTest {

    @Autowired MemberRepository memberRepository;
//    @Autowired OrderRepository orderRepository;


    public void createMember() {
        MemberDto memberDto = new MemberDto();

        for (int i = 0; i < 10; i++) {
            memberDto.setName("테스트");
            memberDto.setAddress(new Address("1","길","도시"));
            memberDto.setEmail("test"+i+"@naver.com");
            memberDto.setPassword("1234");
            memberDto.setRole(Role.ROLE_MEMBER);
            memberDto.setPhoneNum("01044440220");
            Member member = new Member(memberDto);
            memberRepository.save(member);
        }
    }

    public void createOrder() {

    }
}