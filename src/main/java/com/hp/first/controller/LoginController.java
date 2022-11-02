package com.hp.first.controller;

import com.hp.first.dto.MemberDto;
import com.hp.first.entity.Member;
import com.hp.first.exception.DuplicationIdException;
import com.hp.first.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;
    @GetMapping
    public String loginPage() {
        return "/login/login";
    }

    @PostMapping
    public String newMember(@RequestBody MemberDto memberDto) {
        if(memberService.checkDuplicate(memberDto.getEmail())) {
            new DuplicationIdException("중복된 이메일입니다.");
            return "중복된 이메일로 인해 저장실패";
        }
        memberService.save(memberDto);
        return "저장완료";
    }

    @PutMapping("/{memberId}")
    public String modMember(@RequestBody MemberDto memberDto, @PathVariable Long memberId) {
        memberDto.setId(memberId);
        memberService.update(memberDto);
        return "수정완료";
    }

    @DeleteMapping("/{memberId}")
    public String delMember(@PathVariable Long memberId) {
        memberService.delete(memberId);
        return "삭제완료";
    }
}
