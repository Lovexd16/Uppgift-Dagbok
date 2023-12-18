package com.diary.diary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewDiaryController {

    @Autowired
    private DiaryRepository diaryRepository;

    @GetMapping("/diary/{diaryTitle}")
    String getDiary(@PathVariable String diaryTitle, Model model) {
        System.out.println("Du gick in på: " + diaryTitle);

        Diary diary = diaryRepository.findByTitle(diaryTitle);

       model.addAttribute("diary", diary);

        return "viewDiary";
    }
    
}
