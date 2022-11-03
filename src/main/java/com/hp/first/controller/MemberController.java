package com.hp.first.controller;

import com.hp.first.dto.MemberDto;
import com.hp.first.entity.Member;
import com.hp.first.exception.DuplicationIdException;
import com.hp.first.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String login() {
        return "/member/memberLoginForm";
    }

    @GetMapping("/error")
    public String loginError(Model model) {
        model.addAttribute("loginError","아이디 또는 비밀번호를 확인해주세요.");
        return "/member/memberLoginForm";
    }

    @GetMapping("/new")
    public String newMember() {
        return "/login/login";
    }

    @PostMapping("/new")
    public String newMember(@Valid MemberDto memberDto, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "/login/login";
        }

        try{
            Member member = Member.createMember(memberDto, passwordEncoder);
            memberService.save(member);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage",e.getMessage());
            return "/login/login";
        }
        return "redirect:/";
    }

   /* @PutMapping("/{memberId}")
    public String modMember(@RequestBody MemberDto memberDto, @PathVariable Long memberId) {
        memberDto.setId(memberId);
        memberService.update(memberDto);
        return "수정완료";
    }
*/
    @DeleteMapping("/{memberId}")
    public String delMember(@PathVariable Long memberId) {
        memberService.delete(memberId);
        return "삭제완료";
    }
}
