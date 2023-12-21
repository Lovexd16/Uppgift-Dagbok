package com.diary.diary;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

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
    
    //Visar de inlägg som inte ligger i framtiden
    @GetMapping
    public String getIndex(Model model) {
        Date currentDate = java.sql.Date.valueOf(LocalDate.now());
        List<Diary> diaries = diaryRepository.relevantDiaries(currentDate);
        model.addAttribute("diaries", diaries);

        return "index";
    }

    //PostMapping för att lägga till ett nytt inlägg
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

    //GetMapping för att ta bort inlägg genom id
    @GetMapping("/delete-diary")
    public String delete(@RequestParam int id) {

        System.out.println("Tog bort inlägg med id: " + id);
        diaryRepository.deleteById(id);

        return "redirect:/";
    }

    @GetMapping("/diaries-between-dates")
    public String findDiariesBetweenDates() {
        return "betweenDatesDiary";
    }

    //GetMapping för att visa en lista med inlägg mellan valda datum
    @GetMapping("/between-dates-diary-result")
    public String showBetweenDatesDiaryResult(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate, Model model) {

        System.out.println("Du sökte mellan datumen: " + startDate + " och " + endDate);

        List<Diary> diaries = diaryRepository.relevantDiaries(java.sql.Date.valueOf(LocalDate.now()));
        List<Diary> diariesBetweenDates = diaryRepository.betweenDatesDiaries(startDate, endDate);
        model.addAttribute("diaries", diaries);
        model.addAttribute("diariesBetweenDates", diariesBetweenDates);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        return "index";
    }
}
