package com.diary.diary;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DiaryController {

    @Autowired
    private DiaryRepository diaryRepository;
    
    @GetMapping
    public String getIndex(Model model) {

        model.addAttribute("diaries", diaryRepository.findAll());

        return "index";
    }

    @PostMapping("/new-diary")
    public String addNew(@RequestParam("title") String diaryTitle, @RequestParam("text") String diaryText, @RequestParam("date") Date diaryDate) {
        System.out.println("Nytt inlägg! Titel: " + diaryTitle + ", Text: " + diaryText + ", Datum: " + diaryDate);

        Diary diary = new Diary();
        diary.setTitle(diaryTitle);
        diary.setText(diaryText);
        diary.setDate(diaryDate);
        diaryRepository.save(diary);

        return "redirect:/";
    }

    @GetMapping("/delete-diary")
    public String delete(@RequestParam int id) {

        System.out.println("Tog bort inlägg med id: " + id);
        diaryRepository.deleteById(id);

        return "redirect:/";
    }
}
