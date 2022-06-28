package com.app.ELearning.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tutorId;
    @NotBlank
    private String tutorName;
    @Column(unique = true)
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    @OneToMany(targetEntity = Class.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tutor_id", referencedColumnName = "tutorId")
    private List<Class> tutorCreatedClasses = new ArrayList<>();

    public Tutor() {
    }

    public Tutor(@NotBlank String tutorName, @NotBlank String username, @NotBlank String password) {
        this.tutorName = tutorName;
        this.username = username;
        this.password = password;
    }

    public Tutor(Integer tutorId, @NotBlank String tutorName, @NotBlank String username, @NotBlank String password) {
        this.tutorId = tutorId;
        this.tutorName = tutorName;
        this.username = username;
        this.password = password;
    }

    public Integer getTutorId() {
        return tutorId;
    }

    public void setTutorId(Integer tutorId) {
        this.tutorId = tutorId;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
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

    public List<Class> getTutorCreatedClasses() {
        return tutorCreatedClasses;
    }

    public void setTutorCreatedClasses(List<Class> tutorCreatedClasses) {
        this.tutorCreatedClasses = tutorCreatedClasses;
    }
}
