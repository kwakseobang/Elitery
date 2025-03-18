package com.kwakmunsu.elitery.member.infrastruction;


import com.kwakmunsu.elitery.auth.oauth2.userinfo.OAuth2UserInfo;
import com.kwakmunsu.elitery.member.entity.Member;
import com.kwakmunsu.elitery.member.entity.SocialType;

public interface MemberAppender {

    Member create(OAuth2UserInfo userInfo, SocialType socialType);

}