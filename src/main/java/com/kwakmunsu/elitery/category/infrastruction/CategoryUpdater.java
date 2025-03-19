package com.kwakmunsu.elitery.category.infrastruction;


import com.kwakmunsu.elitery.category.entity.Category;
import com.kwakmunsu.elitery.category.repository.CategoryRepository;
import com.kwakmunsu.elitery.global.exception.DuplicationException;
import com.kwakmunsu.elitery.global.response.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CategoryUpdater {

    private final CategoryRepository categoryRepository;

    @Transactional
    public void update(Long categoryId, String newCategory) {
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new DuplicationException(ErrorCode.DUPLICATE_CATEGORY, newCategory));
        category.updateTitle(newCategory);
    }

}