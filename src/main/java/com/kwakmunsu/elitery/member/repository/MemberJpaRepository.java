package com.kwakmunsu.elitery.member.repository;

import com.kwakmunsu.elitery.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    Optional<Member> findBySocialId(String socialId);
}