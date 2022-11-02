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
        memberDto.setRole(Role.ROLE_ADMIN);
        Member saveMember = new Member(memberDto);
        memberRepository.save(saveMember);
    }

    @Transactional
    public void delete(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    public boolean checkDuplicate(String memberEmail) {
        return memberRepository.existsByEmail(memberEmail);
    }

    @Transactional
    public void update(MemberDto memberDto) {
        Member member = memberRepository
                .findById(memberDto.getId())
                .orElseThrow(DuplicationIdException::new);

        member.modMember(memberDto);
    }
}
