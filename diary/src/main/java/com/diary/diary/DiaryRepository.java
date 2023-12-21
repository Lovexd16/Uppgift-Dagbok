package com.diary.diary;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DiaryRepository extends CrudRepository<Diary, Integer> {
    //Hitta inlägg genom dens titel
    Diary findByTitle(String title);

    //Hitta inlägg som inte ligger i framtiden
    @Query("SELECT d FROM Diary d WHERE d.date <= :currentDate")
    List<Diary> relevantDiaries(@Param("currentDate") Date currentDate);

    //Hitta inlägg mellan valda datum
    @Query("SELECT d FROM Diary d WHERE d.date BETWEEN :startDate AND :endDate")
    List<Diary> betweenDatesDiaries(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
