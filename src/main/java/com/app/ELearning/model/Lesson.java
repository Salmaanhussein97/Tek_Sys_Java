package com.app.ELearning.model;

import javax.persistence.*;

@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String lessonName;
    @Column(name = "lesson_time")
    private String lessonTime;

    public Lesson() {
    }

    public Lesson(Integer id, String lessonName, String lessonTime) {
        this.id = id;
        this.lessonName = lessonName;
        this.lessonTime = lessonTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(String lessonTime) {
        this.lessonTime = lessonTime;
    }
}
