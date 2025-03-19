package com.kwakmunsu.elitery.category.infrastruction;

import com.kwakmunsu.elitery.category.repository.CategoryRepository;
import com.kwakmunsu.elitery.global.exception.NotFoundException;
import com.kwakmunsu.elitery.global.response.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CategoryRemover {

    private final CategoryRepository categoryRepository;

    @Transactional
    public void delete(Long categoryId) {
        if (categoryRepository.existById(categoryId)) {
            categoryRepository.delete(categoryId);
            return;
        }
        throw new NotFoundException(ErrorCode.NOT_FOUND_CATEGORY);
    }

}