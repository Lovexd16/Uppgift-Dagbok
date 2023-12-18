package com.diary.diary;

import org.springframework.data.repository.CrudRepository;

public interface DiaryRepository extends CrudRepository<Diary, Integer> {
    Diary findByTitle(String title);
}
