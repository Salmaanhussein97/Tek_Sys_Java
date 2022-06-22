package com.mytech.postdelivery.service;

import com.mytech.postdelivery.exception.NotFoundException;
import com.mytech.postdelivery.model.Delivery;
import com.mytech.postdelivery.model.Rider;
import com.mytech.postdelivery.model.User;
import com.mytech.postdelivery.repository.DeliveryRepository;
import com.mytech.postdelivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Delivery addDelivery(Delivery delivery) throws NotFoundException {
        User user = userService.findUserById(delivery.getUserId());
        Rider rider = riderService.findRiderById(delivery.getRiderId());
        delivery.setUser(user);
        delivery.setRider(rider);
        return deliveryRepository.save(delivery);
    }

    public Delivery findDeliveryById(int id) throws NotFoundException {
        return deliveryRepository.findById(id).orElseThrow(() -> new NotFoundException("delivery not found for id = " + id));
    }

    public List<Delivery> getAllDeliveriesByUserId(int userid) throws NotFoundException {
        User findUser = userService.findUserById(userid);
        if(findUser==null)
        {
            throw new NotFoundException("user not found");
        }
                    System.out.println(userid);
        return deliveryRepository.findDeliveryByUserId(userid);
    }

    public Delivery updateUserInfo(Delivery user) throws NotFoundException {
        Delivery findUser = findDeliveryById(user.getUserId());
        if (findUser==null)
        {
            throw new NotFoundException("user does not exist");
        }
        return deliveryRepository.save(user);

        }
    public Delivery removeUser(int id) throws NotFoundException {
        Delivery findUser = findDeliveryById(id);
        if(findUser==null)
        {
            throw new NotFoundException("user not found");
        }
        deliveryRepository.deleteById(id);
        return findUser;
    }

}
