package com.kwakmunsu.elitery.diary.repository;

import com.kwakmunsu.elitery.diary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryJpaRepository extends JpaRepository<Diary, Long> {

}