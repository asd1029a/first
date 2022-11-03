package com.hp.first.repository;

import com.hp.first.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Boolean existsByEmail(String email);

    Member findByEmail(String email);
}
