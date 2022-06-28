package com.app.ELearning.repository;

import com.app.ELearning.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {
    @Query(value = "select * from Class", nativeQuery = true)
    List<Class> findAllClasses();
}
