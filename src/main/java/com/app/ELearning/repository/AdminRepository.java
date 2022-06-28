package com.app.ELearning.repository;

import com.app.ELearning.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findAdminByUsername(String username);

    Optional<Admin> findAdminByUsernameAndPassword(String username, String password);
}
