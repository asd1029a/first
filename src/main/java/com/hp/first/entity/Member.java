package com.hp.first.entity;

import com.hp.first.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BasicEntity implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String email;

    private String password;

    private String phoneNum;

    @OneToMany(mappedBy = "member")
    private List<Order> orderList = new ArrayList<>();

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
    public void modMember(MemberDto memberDto) {
        this.password = memberDto.getPassword();
        this.phoneNum = memberDto.getPhoneNum();
        this.address = memberDto.getAddress();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
