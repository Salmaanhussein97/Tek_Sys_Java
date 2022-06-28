package com.app.ELearning.repository;

import com.app.ELearning.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Integer> {
    Optional<Tutor> findTutorByUsernameAndPassword(String username, String password);

    Optional<Tutor> findTutorByUsername(String username);

    @Query(value = "select * from Tutor", nativeQuery = true)
    List<Tutor> findAllTutors();
}
