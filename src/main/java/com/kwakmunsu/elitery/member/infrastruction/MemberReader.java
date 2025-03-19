package com.kwakmunsu.elitery.member.infrastruction;

import com.kwakmunsu.elitery.member.entity.Member;
import com.kwakmunsu.elitery.member.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class MemberReader {

    private final MemberRepository memberRepository;

    public Optional<Member> read(String socialId) {
        return memberRepository.read(socialId);
    }

}