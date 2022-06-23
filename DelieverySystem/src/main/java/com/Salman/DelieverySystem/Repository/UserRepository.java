package com.Salman.DelieverySystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Salman.DelieverySystem.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select user from User user where user.email=?1 and user.enabled=true")
	User findByEmail(String email);

	@Query("select user from User user where user.email=?1 and user.password=?2")
	User findByUserPassword(String username, String password);
}
