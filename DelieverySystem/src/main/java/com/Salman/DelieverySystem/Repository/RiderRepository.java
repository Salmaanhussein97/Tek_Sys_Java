package com.Salman.DelieverySystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Salman.DelieverySystem.Model.Rider;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Integer> {

	@Query("select rider from Rider rider where rider.email=?1 and rider.enabled=true")
	Rider findByEmail(String email);

	@Query("select rider from Rider rider where rider.email=?1 and rider.password=?2")
	Rider findByUserPassword(String username, String password);
}
