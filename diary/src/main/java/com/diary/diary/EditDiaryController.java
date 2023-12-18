package com.diary.diary;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EditDiaryController {
    
    @Autowired
    private DiaryRepository diaryRepository;

    @GetMapping("/edit-diary/{id}")
    public String edit(@PathVariable int id, Model model) {
        System.out.println("Du tryckte edit på: " + id);
        Diary diary = diaryRepository.findById(id).orElse(null);
        if (diary != null) {
            model.addAttribute("diary", diary);
            return "editDiary";
        } else {
            return "redirect:/";
        }
    }

    @Transactional
    @PostMapping("/update-diary")
    public String update(@RequestParam int id, @RequestParam("title") String diaryTitle, 
    @RequestParam("text") String diaryText, @RequestParam("date") Date diaryDate) {

        Diary existingDiary = diaryRepository.findById(id).orElse(null);

        if (existingDiary != null) {
            System.out.println("Du ändrade titel till: " + diaryTitle + ", Text till: " + diaryText + ", och datum till: " + diaryDate);
            existingDiary.setTitle(diaryTitle);
            existingDiary.setText(diaryText);
            existingDiary.setDate(diaryDate);
        }
        return "redirect:/";
    }
}
