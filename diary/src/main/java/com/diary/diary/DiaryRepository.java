package com.diary.diary;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DiaryRepository extends CrudRepository<Diary, Integer> {
    Diary findByTitle(String title);

    @Query("SELECT d FROM Diary d WHERE d.date <= :currentDate")
    List<Diary> relevantDiaries(@Param("currentDate") Date currentDate);
}
