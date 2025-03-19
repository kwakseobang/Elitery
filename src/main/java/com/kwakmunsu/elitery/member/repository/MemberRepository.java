package com.kwakmunsu.elitery.member.repository;

import com.kwakmunsu.elitery.member.entity.Member;
import java.util.Optional;

public interface MemberRepository {

    Member append(Member member);
    Optional<Member> read(String socialId);
}