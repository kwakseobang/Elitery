package com.kwakmunsu.elitery.member.infrastruction;


import com.kwakmunsu.elitery.auth.oauth2.userinfo.OAuth2UserInfo;
import com.kwakmunsu.elitery.member.entity.Member;
import com.kwakmunsu.elitery.member.entity.Role;
import com.kwakmunsu.elitery.member.entity.SocialType;
import com.kwakmunsu.elitery.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberAppender {

    private final MemberRepository memberRepository;

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

        return memberRepository.append(member);
    }

}