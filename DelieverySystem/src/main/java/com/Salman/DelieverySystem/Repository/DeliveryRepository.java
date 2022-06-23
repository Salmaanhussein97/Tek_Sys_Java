package com.Salman.DelieverySystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Salman.DelieverySystem.Model.Delivery;


@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Integer>{

	 @Query("select delivery from Delivery delivery where delivery.user.id=?1")
	    List<Delivery> findDeliveryByUserId(int userId);
}
