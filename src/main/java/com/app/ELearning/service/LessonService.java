package com.app.ELearning.service;

import com.app.ELearning.model.Lesson;
import com.app.ELearning.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;

    @Autowired
    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public Lesson save(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public List<Lesson> list() {
        try {
            return lessonRepository.findAllLessons();
        } catch (Exception e) {
            throw new RuntimeException("There is no lesson in the database");
        }
    }

    public void delete(Integer lessonId) {
        Optional<Lesson> lesson = lessonRepository.findById(lessonId);
        lesson.ifPresent(lessonRepository::delete);
    }
}
