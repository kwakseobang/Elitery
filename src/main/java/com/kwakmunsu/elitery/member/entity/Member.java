package com.kwakmunsu.elitery.member.entity;

import com.kwakmunsu.elitery.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "nickname", unique = true, nullable = false)
    private String nickname;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "social_login_id", unique = true, nullable = false)
    private String socialLoginId;

    @Column(name = "social_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(name = "introduction")
    private String introduction;

    // 빌더 패턴에서 값이 들어오지 않을 경우 기본 타입은 0,false 같은 타입 저장, 참조 타입은 null
    @Builder
    public Member(
        String email,
        String nickname,
        String imageUrl,
        Role role,
        String socialLoginId,
        SocialType socialType,
        String introduction
    ) {
        this.email = email;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
        this.role = role;
        this.socialLoginId = socialLoginId;
        this.socialType = socialType;
        this.introduction = introduction;
    }

}