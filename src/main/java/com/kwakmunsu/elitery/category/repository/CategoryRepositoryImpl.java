package com.kwakmunsu.elitery.category.repository;


import com.kwakmunsu.elitery.category.entity.Category;
import com.kwakmunsu.elitery.global.exception.NotFoundException;
import com.kwakmunsu.elitery.global.response.error.ErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {

    private final CategoryJpaRepository categoryJpaRepository;

    @Override
    public boolean existById(Long categoryId) {
        return categoryJpaRepository.existsById(categoryId);
    }

    @Override
    public void append(Category category) {
        categoryJpaRepository.save(category);
    }

    @Override
    public Category read(Long categoryId) {
        return categoryJpaRepository.findById(categoryId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_CATEGORY));
    }

    @Override
    public List<Category> readAll() {
        return categoryJpaRepository.findAll();
    }

    @Override
    public void delete(Long categoryId) {
        categoryJpaRepository.deleteById(categoryId);
    }

    @Override
    public Category findById(Long categoryId) {
        return categoryJpaRepository.findById(categoryId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_CATEGORY));
    }

}