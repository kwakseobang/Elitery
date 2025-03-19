package com.kwakmunsu.elitery.category.infrastruction;

import com.kwakmunsu.elitery.category.entity.Category;
import com.kwakmunsu.elitery.category.repository.CategoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryReader {

    private final CategoryRepository categoryRepository;

    public Category read(Long categoryId) {
        return categoryRepository.read(categoryId);
    }

    public List<Category> readAll() {
        return categoryRepository.readAll();
    }

}