package com.mytech.postdelivery.service;

import com.mytech.postdelivery.exception.NotFoundException;
import com.mytech.postdelivery.model.Delivery;
import com.mytech.postdelivery.model.Order;
import com.mytech.postdelivery.model.Rider;
import com.mytech.postdelivery.model.User;
import com.mytech.postdelivery.repository.DeliveryRepository;
import com.mytech.postdelivery.repository.OrderRepository;
import com.mytech.postdelivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private RiderService riderService;
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order addOrder(Order order) throws NotFoundException {
        User user = userService.findUserById(order.getUserId());
        Rider rider = riderService.findRiderById(order.getRiderId());
        order.setUser(user);
        order.setRider(rider);
        return orderRepository.save(order);
    }

    public Order findOrderById(int id) throws NotFoundException {
        return orderRepository.findById(id).orElseThrow(() -> new NotFoundException("order not found for id = " + id));
    }

    public List<Order> getAllOrdersByUserId(int userid) throws NotFoundException {
        User findUser = userService.findUserById(userid);
        if(findUser==null)
        {
            throw new NotFoundException("user not found");
        }
                    System.out.println(userid);
        return orderRepository.findOrderByUserId(userid);
    }

    public Order removeUser(int id) throws NotFoundException {
        Order findUser = findOrderById(id);
        if (findUser==null)
        {
            throw new NotFoundException("order doesnt exist");
        }
        orderRepository.delete(findUser);
        return findUser;
    }

    public Order updateUserInfo(Order user) throws NotFoundException {
        Order findUser = findOrderById(user.getId());
        if (findUser==null)
        {
            throw new NotFoundException("user does not exist");
        }
        return orderRepository.save(user);
    }
}
