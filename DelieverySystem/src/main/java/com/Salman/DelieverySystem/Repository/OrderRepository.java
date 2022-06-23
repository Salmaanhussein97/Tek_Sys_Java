package com.Salman.DelieverySystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Salman.DelieverySystem.Model.Order;

@Repository
public interface OrderRepository  extends JpaRepository<Order, Integer>{

	   @Query("select order from Order order where order.user.id=?1")
	    List<Order> findOrderByUserId(int userId);
}
