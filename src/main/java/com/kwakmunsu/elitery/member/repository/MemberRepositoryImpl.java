package com.kwakmunsu.elitery.member.repository;

import com.kwakmunsu.elitery.auth.oauth2.userinfo.OAuth2UserInfo;
import com.kwakmunsu.elitery.member.entity.Member;
import com.kwakmunsu.elitery.member.entity.Role;
import com.kwakmunsu.elitery.member.entity.SocialType;
import com.kwakmunsu.elitery.member.infrastruction.MemberAppender;
import com.kwakmunsu.elitery.member.infrastruction.MemberReader;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberReader, MemberAppender {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Member create(OAuth2UserInfo userInfo, SocialType socialType) {
        Member member = Member.builder()
            .email(userInfo.getProvider() + ": " + userInfo.getEmail()) // Naver: iii6602@naver.com
            .nickname(userInfo.getName())
            .imageUrl(null) // 명시적으로 하기 위해서
            .role(Role.MEMBER)
            .socialType(socialType)
            .socialId(userInfo.getSocialId())
            .introduction("")
            .build();
        log.info("새로운 회원 생성 {} ", member);
        return memberJpaRepository.save(member);

    }

    @Override
    public Optional<Member> getMember(String socialId) {
        return memberJpaRepository.findBySocialId(socialId);
    }

}