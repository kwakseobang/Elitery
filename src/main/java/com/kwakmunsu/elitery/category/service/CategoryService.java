package com.kwakmunsu.elitery.category.service;

import com.kwakmunsu.elitery.category.entity.Category;
import java.util.List;

public interface CategoryService {

    void create(String category);
    Category read(Long categoryId);
    List<Category> readAll();
    void update(Long categoryId, String category);
    void delete(Long category);

}