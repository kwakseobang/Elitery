package com.kwakmunsu.elitery.category.entity;

import com.kwakmunsu.elitery.global.entity.BaseEntity;
import com.kwakmunsu.elitery.global.exception.BadRequestException;
import com.kwakmunsu.elitery.global.response.error.ErrorCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Builder
    public Category(String title) {
        validateTitle(title);
        this.title = title;
    }

    public void updateTitle(String title) {
        validateTitle(title);
        this.title = title;
    }

    private void validateTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new BadRequestException(ErrorCode.BAD_REQUEST_CATEGORY);
        }
    }

}