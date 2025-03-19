package com.kwakmunsu.elitery.member.repository;

import com.kwakmunsu.elitery.member.entity.Member;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Member append(Member member) {
        log.info("새로운 회원 생성 {} ", member);
        return memberJpaRepository.save(member);
    }

    @Override
    public Optional<Member> read(String socialId) {
        return memberJpaRepository.findBySocialId(socialId);
    }

}