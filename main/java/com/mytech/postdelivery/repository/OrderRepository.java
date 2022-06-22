package com.mytech.postdelivery.repository;

import com.mytech.postdelivery.model.Delivery;
import com.mytech.postdelivery.model.Order;
import com.mytech.postdelivery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("select order from Order order where order.user.id=?1")
    List<Order> findOrderByUserId(int userId);
}
