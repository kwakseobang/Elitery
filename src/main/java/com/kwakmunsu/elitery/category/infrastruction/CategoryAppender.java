package com.kwakmunsu.elitery.category.infrastruction;


import com.kwakmunsu.elitery.category.entity.Category;
import com.kwakmunsu.elitery.category.repository.CategoryRepository;
import com.kwakmunsu.elitery.global.exception.DuplicationException;
import com.kwakmunsu.elitery.global.response.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryAppender {

    private final CategoryRepository categoryRepository;

    public void append(String title) {
        validateCategory(title);
        Category category = Category.builder()
            .title(title)
            .build();
        categoryRepository.append(category);
    }

    private void validateCategory(String title) {
        if (categoryRepository.existByCategory(title)) {
            throw new DuplicationException(ErrorCode.DUPLICATE_CATEGORY, title);
        }
    }

}