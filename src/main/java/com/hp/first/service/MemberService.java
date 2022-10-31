package com.hp.first.service;

import com.hp.first.dto.MemberDto;
import com.hp.first.entity.Member;
import com.hp.first.entity.Role;
import com.hp.first.exception.DuplicationIdException;
import com.hp.first.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void save(MemberDto memberDto) {
        memberRepository.findById(memberDto.getId()).orElseGet(() -> {
            memberDto.setRole(Role.ROLE_ADMIN);
            if (!checkDuplicate(memberDto)) {
                Member member = new Member(memberDto);
                memberRepository.save(member);
                return member;
            } else {
                throw new DuplicationIdException();
            }
        });
    }

    public void delete(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    public boolean checkDuplicate(MemberDto memberDto) {
        String findEmail = memberDto.getEmail();
//        memberRepository.findByEmail(findEmail).orElseThrow(DuplicationIdException::new);
        return memberRepository.existsByEmail(findEmail);
    }
}
