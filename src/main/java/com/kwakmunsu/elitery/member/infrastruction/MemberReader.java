package com.kwakmunsu.elitery.member.infrastruction;

import com.kwakmunsu.elitery.member.entity.Member;
import java.util.Optional;

public interface MemberReader {

    Optional<Member> getMember(String socialId);

}