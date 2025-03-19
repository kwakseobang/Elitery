package com.kwakmunsu.elitery.category.repository;

import com.kwakmunsu.elitery.category.entity.Category;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<Category, Long> {
    boolean existsByTitle(String title);
    Optional<Category> findByTitle(String title);
    void deleteByTitle(String title);
}