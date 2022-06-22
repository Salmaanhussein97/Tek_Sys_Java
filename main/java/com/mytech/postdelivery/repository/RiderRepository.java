package com.mytech.postdelivery.repository;

import com.mytech.postdelivery.model.Delivery;
import com.mytech.postdelivery.model.Rider;
import com.mytech.postdelivery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RiderRepository extends JpaRepository<Rider, Integer> {

    @Query("select rider from Rider rider where rider.email=?1 and rider.enabled=true")
    Rider findByEmail(String email);

    @Query("select rider from Rider rider where rider.email=?1 and rider.password=?2")
    Rider findByUserPassword(String username, String password);

}
