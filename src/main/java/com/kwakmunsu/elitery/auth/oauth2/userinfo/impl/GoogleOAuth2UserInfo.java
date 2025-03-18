package com.kwakmunsu.elitery.auth.oauth2.userinfo.impl;

import static com.kwakmunsu.elitery.member.entity.SocialType.GOOGLE;

import com.kwakmunsu.elitery.auth.oauth2.userinfo.OAuth2UserInfo;
import java.util.Map;

public class GoogleOAuth2UserInfo implements OAuth2UserInfo {

    private final Map<String, Object> attributes;

    public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProvider() {
        return GOOGLE.name();
    }

    @Override
    public String getSocialId() {
        return attributes.get("sub").toString();
    }

    @Override
    public String getEmail() {
        return attributes.get("email").toString();
    }

    @Override
    public String getName() {
        return attributes.get("name").toString();
    }

}