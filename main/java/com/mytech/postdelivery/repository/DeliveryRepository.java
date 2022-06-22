package com.mytech.postdelivery.repository;

import com.mytech.postdelivery.model.Delivery;
import com.mytech.postdelivery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
    @Query("select delivery from Delivery delivery where delivery.user.id=?1")
    List<Delivery> findDeliveryByUserId(int userId);
}
