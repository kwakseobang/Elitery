package com.kwakmunsu.elitery.category.service;

import com.kwakmunsu.elitery.category.entity.Category;
import com.kwakmunsu.elitery.category.infrastruction.CategoryAppender;
import com.kwakmunsu.elitery.category.infrastruction.CategoryReader;
import com.kwakmunsu.elitery.category.infrastruction.CategoryRemover;
import com.kwakmunsu.elitery.category.infrastruction.CategoryUpdater;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryAppender categoryAppender;
    private final CategoryReader categoryReader;
    private final CategoryRemover categoryRemover;
    private final CategoryUpdater categoryUpdater;

    @Override
    public void create(String category) {
        categoryAppender.append(category);
    }

    @Override
    public Category read(Long categoryId) {
        return categoryReader.read(categoryId);
    }

    @Override
    public List<Category> readAll() {
        return categoryReader.readAll();
    }

    @Override
    public void update(Long categoryId, String category) {
        categoryUpdater.update(categoryId, category);
    }

    @Override
    public void delete(Long categoryId) {
        categoryRemover.delete(categoryId);
    }

}