package com.app.ELearning;

import com.app.ELearning.model.Lesson;
import com.app.ELearning.repository.LessonRepository;
import com.app.ELearning.service.LessonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class LessonServiceTest {

    @Autowired
    private LessonService lessonService;
    @MockBean
    private LessonRepository lessonRepository;

    @Test
    public void createLessonTest() {
        Lesson lesson = new Lesson();
        lesson.setId(1);
        lesson.setLessonName("Introduction");
        lesson.setLessonTime("09:00");

        when(lessonRepository.save(lesson)).thenReturn(lesson);
        assertEquals(lesson, lessonService.save(lesson));
    }

    @Test
    public void listOfLessonsTest() {
        Lesson lesson = new Lesson();
        lesson.setId(1);
        lesson.setLessonName("Introduction");
        lesson.setLessonTime("09:00");

        List<Lesson> lessonsList = new ArrayList<>();
        lessonsList.add(lesson);

        when(lessonRepository.findAllLessons()).thenReturn(lessonsList);
        assertEquals(lessonsList, lessonService.list());
    }
}
