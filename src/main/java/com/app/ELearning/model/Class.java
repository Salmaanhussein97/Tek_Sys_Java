package com.app.ELearning.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Class {

    @Column(name = "class_time")
    private String classTime;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer classId;
    @Column(name = "class_name")
    private String className;
    @Column(name = "days_per_week")
    private int daysPerWeek;
    @Column(name = "tutor_name", nullable = true)
    private String tutorName;

    @OneToMany(targetEntity = Lesson.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "class_id", referencedColumnName = "classId")
    private List<Lesson> classLessons = new ArrayList<>();

    public Class() {
    }

    public Class(String classTime, Integer classId, String className, int daysPerWeek, String tutorName, List<Lesson> classLessons) {
        this.classTime = classTime;
        this.classId = classId;
        this.className = className;
        this.daysPerWeek = daysPerWeek;
        this.tutorName = tutorName;
        this.classLessons = classLessons;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getDaysPerWeek() {
        return daysPerWeek;
    }

    public void setDaysPerWeek(int daysPerWeek) {
        this.daysPerWeek = daysPerWeek;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public List<Lesson> getClassLessons() {
        return classLessons;
    }

    public void setClassLessons(List<Lesson> classLessons) {
        this.classLessons = classLessons;
    }
}
