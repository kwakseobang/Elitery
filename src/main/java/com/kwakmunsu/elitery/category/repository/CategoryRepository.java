package com.kwakmunsu.elitery.category.repository;


import com.kwakmunsu.elitery.category.entity.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    void append(Category category);
    boolean existByCategory(String title);
    boolean existById(Long categoryId);

    Category read(Long categoryId);
    List<Category> readAll();

    Optional<Category> findById(Long categoryId);

    void delete(Long categoryId);


}