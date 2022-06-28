package com.app.ELearning.service;

import com.app.ELearning.model.Tutor;
import com.app.ELearning.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorService {

    private final TutorRepository tutorRepository;

    @Autowired
    public TutorService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    public Tutor save(Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    public List<Tutor> list() {
        return tutorRepository.findAllTutors();
    }

    public void delete(Integer tutorId) {
        Optional<Tutor> tutor = tutorRepository.findById(tutorId);
        tutor.ifPresent(tutorRepository::delete);
    }

    public Optional<Tutor> getTutorById(Integer tutorId) {
        return tutorRepository.findById(tutorId);
    }

}
