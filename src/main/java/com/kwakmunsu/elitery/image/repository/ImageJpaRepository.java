package com.kwakmunsu.elitery.image.repository;

import com.kwakmunsu.elitery.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageJpaRepository extends JpaRepository<Image, Long> {

}