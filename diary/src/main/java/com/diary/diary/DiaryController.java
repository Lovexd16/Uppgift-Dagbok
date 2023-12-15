package com.diary.diary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiaryController {

    @Autowired
    private DiaryRepository diaryRepository;
    
    @GetMapping
    public String getIndex(Model model) {

        model.addAttribute("diaries", diaryRepository.findAll());

        return "index";
    }

    @GetMapping("/new-diary")
    public String addNew() {

        Diary diary = new Diary();
        diary.setTitle("Ny diary");
        diaryRepository.save(diary);

        return "redirect:/";
    }
}
