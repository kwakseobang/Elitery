package com.kwakmunsu.elitery.auth.oauth2.dto;

import com.kwakmunsu.elitery.member.entity.Member;
import com.kwakmunsu.elitery.member.entity.Role;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOAuth2Member implements OAuth2User {

    private final Member member;
    private final Map<String, Object> attributes;

    public CustomOAuth2Member(Member member, Map<String, Object> attributes) {
        this.member = member;
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add((GrantedAuthority) () -> member.getRole().getValue());
        return collection;
    }

    @Override
    public String getName() {
        return member.getNickname();
    }

    public Role getRole() {
        return member.getRole();
    }

    public Long getMemberId() {
        return member.getId();
    }

}