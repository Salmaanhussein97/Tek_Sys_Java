package com.Salman.DelieverySystem.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Salman.DelieverySystem.Model.Order;
import com.Salman.DelieverySystem.Model.Rider;
import com.Salman.DelieverySystem.Model.User;
import com.Salman.DelieverySystem.Repository.OrderRepository;

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

	public Order addOrder(Order order) {
		User user = userService.findUserById(order.getUserId());
		Rider rider = riderService.findRiderById(order.getRiderId());
		order.setUser(user);
		order.setRider(rider);
		return orderRepository.save(order);
	}

	public Order findOrderById(int id) {
		return orderRepository.findById(id).orElseThrow(null);
	}

	public List<Order> getAllOrdersByUserId(int userid) {
		User findUser = userService.findUserById(userid);

		return orderRepository.findOrderByUserId(userid);
	}

	public Order removeUser(int id) {
		Order findUser = findOrderById(id);

		orderRepository.delete(findUser);
		return findUser;
	}

	public Order updateUserInfo(Order user) {
		Order findUser = findOrderById(user.getId());

		return orderRepository.save(user);
	}
}
