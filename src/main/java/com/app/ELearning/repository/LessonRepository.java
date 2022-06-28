package com.app.ELearning.repository;

import com.app.ELearning.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    @Query(value = "select * from Lesson", nativeQuery = true)
    List<Lesson> findAllLessons();
}
