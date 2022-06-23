package com.Salman.DelieverySystem.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Salman.DelieverySystem.Model.Delivery;
import com.Salman.DelieverySystem.Model.Rider;
import com.Salman.DelieverySystem.Model.User;
import com.Salman.DelieverySystem.Repository.DeliveryRepository;


@Service
public class DeliveryService {

	@Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private RiderService riderService;

    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    public Delivery addDelivery(Delivery delivery) {
        User user = userService.findUserById(delivery.getUserId());
        Rider rider = riderService.findRiderById(delivery.getRiderId());
        delivery.setUser(user);
        delivery.setRider(rider);
        return deliveryRepository.save(delivery);
    }

    public Delivery findDeliveryById(int id)  {
        return deliveryRepository.findById(id).orElseThrow(null);
    }

    public List<Delivery> getAllDeliveriesByUserId(int userid)  {
        User findUser = userService.findUserById(userid);
        return deliveryRepository.findDeliveryByUserId(userid);
    }

    public Delivery updateUserInfo(Delivery user)  {
        Delivery findUser = findDeliveryById(user.getUserId());
        return deliveryRepository.save(user);

        }
    public Delivery removeUser(int id)  {
        Delivery findUser = findDeliveryById(id);
        deliveryRepository.deleteById(id);
        return findUser;
    }

}

