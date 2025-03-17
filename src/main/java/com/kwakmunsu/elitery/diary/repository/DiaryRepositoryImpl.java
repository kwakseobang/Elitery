package com.kwakmunsu.elitery.diary.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DiaryRepositoryImpl implements DiaryRepository {

    private final DiaryJpaRepository diaryJpaRepository;

}