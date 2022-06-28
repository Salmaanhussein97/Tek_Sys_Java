package com.app.ELearning.controller;

import com.app.ELearning.model.Class;
import com.app.ELearning.model.Lesson;
import com.app.ELearning.repository.ClassRepository;
import com.app.ELearning.repository.LessonRepository;
import com.app.ELearning.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/lessons")
public class LessonController {
    private final LessonService lessonService;

    @Autowired
    LessonRepository lessonRepository;


    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("/list/{tutorId}")
    public String lessonList(@PathVariable(name = "tutorId") Integer tutorId, Model model) {
        model.addAttribute("tutorId", tutorId);
        model.addAttribute("classId", null);
        model.addAttribute("lessonsList", lessonService.list());
        return "view-lessons";
    }



}
