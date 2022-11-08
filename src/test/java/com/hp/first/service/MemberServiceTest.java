//package com.hp.first.service;
//
//import com.hp.first.dto.MemberDto;
//import com.hp.first.entity.Address;
//import com.hp.first.status.Role;
//import com.hp.first.exception.DuplicationIdException;
//import com.hp.first.repository.MemberRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import javax.annotation.PostConstruct;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.BDDMockito.*;
//
////@SpringBootTest
////@Transactional
//@ExtendWith(MockitoExtension.class)
//class MemberServiceTest {
//
//    @InjectMocks
//    MemberService memberService;
//
//    @Mock
//    MemberRepository memberRepository;
//
//    @PostConstruct
//    public void 먼저() {
//        MemberDto memberDto = new MemberDto();
//        memberDto.setName("테스트");
//        memberDto.setAddress(new Address("1","길","도시"));
//        memberDto.setEmail("test@naver.com");
//        memberDto.setPassword("1234");
//        memberDto.setRole(Role.ROLE_MEMBER);
//        memberDto.setPhoneNum("01044440220");
//        memberService.save(memberDto);
//    }
//
//    @Test
//    @DisplayName("맴버 등록 중복 테스트")
//    public void memberDuplicateTest(){
//        // given
//        doReturn(true).when(memberRepository).existsByEmail(any());
//
//        // when
//        MemberDto memberDto = new MemberDto();
//        memberDto.setName("테스트");
//        memberDto.setAddress(new Address("1","길","도시"));
//        memberDto.setEmail("test@naver.com");
//        memberDto.setPassword("1234");
//        memberDto.setRole(Role.ROLE_MEMBER);
//        memberDto.setPhoneNum("01044440220");
//        assertThrows(DuplicationIdException.class, ()->{
//            memberService.save(memberDto);
//        });
//        // then
//
//}
//
//    @Test
//    @ExceptionHandler()
//    public void 중복테스트() {
//        MemberDto memberDto = new MemberDto();
//        memberDto.setName("테스트");
//        memberDto.setAddress(new Address("1","길","도시"));
//        memberDto.setEmail("test@naver.com");
//        memberDto.setPassword("1234");
//        memberDto.setRole(Role.ROLE_MEMBER);
//        memberDto.setPhoneNum("01044440220");
//
//
//        assertThrows(DuplicationIdException.class, ()->{
//           memberService.save(memberDto);
//        });
//    }
//}