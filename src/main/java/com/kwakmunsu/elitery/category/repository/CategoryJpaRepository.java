package com.kwakmunsu.elitery.category.repository;

import com.kwakmunsu.elitery.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<Category, Long> {

}