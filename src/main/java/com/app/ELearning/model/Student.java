package com.app.ELearning.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;
    @NotBlank
    private String studentName;
    @Column(unique = true)
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    @ManyToMany(targetEntity = Class.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Class> studentBookedClasses = new ArrayList<>();

    public Student() {
    }

    public Student(@NotBlank String studentName, @NotBlank String username, @NotBlank String password) {
        this.studentName = studentName;
        this.username = username;
        this.password = password;
    }

    public Student(Integer studentId, @NotBlank String studentName, @NotBlank String username, @NotBlank String password) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.username = username;
        this.password = password;
    }

    public Student(Integer studentId, @NotBlank String studentName, @NotBlank String username, @NotBlank String password, List<Class> studentBookedClasses) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.username = username;
        this.password = password;
        this.studentBookedClasses = studentBookedClasses;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Class> getStudentBookedClasses() {
        return studentBookedClasses;
    }

    public void setStudentBookedClasses(List<Class> studentBookedClasses) {
        this.studentBookedClasses = studentBookedClasses;
    }
}
