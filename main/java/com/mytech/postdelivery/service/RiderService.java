package com.mytech.postdelivery.service;

import com.mytech.postdelivery.exception.EmptyException;
import com.mytech.postdelivery.exception.InvalidCredentialsException;
import com.mytech.postdelivery.exception.NotFoundException;
import com.mytech.postdelivery.model.Rider;
import com.mytech.postdelivery.model.User;
import com.mytech.postdelivery.repository.RiderRepository;
import com.mytech.postdelivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RiderService {
    @Autowired
    private RiderRepository riderRepository;

    public List<Rider> getAllUsers(){
        return riderRepository.findAll();
    }

    @Transactional
    public Rider addNewRider(Rider rider) throws EmptyException, NotFoundException {
        if (rider.getEmail()==null)
            throw new EmptyException("Please mention email id");
        if (rider.getPassword()==null)
            throw new EmptyException("Please mention strong password");
        if (rider.getPhone()==null)
            throw new EmptyException("Please mention contact number");
        if (rider.getAddress()==null)
            throw new EmptyException("Please mention address");
        if (rider.getName()==null)
            throw new EmptyException("Please mention name");
        if (rider.getRole()==null)
            throw new EmptyException("Please mention role");


        Rider findRider = riderRepository.findByEmail(rider.getEmail());
        if (findRider!=null)
            throw new EmptyException("Rider already exists with provided email account");
        rider.setEnabled(true);
        return riderRepository.save(rider);
    }

    public Rider findRiderById(int id) throws NotFoundException {
        return riderRepository.findById(id).orElseThrow(()-> new NotFoundException("Rider not found for id = "+id));
    }

    public Rider updateRiderInfo(Rider rider) throws NotFoundException {
        Rider findUser = findRiderById(rider.getId());
        if(findUser==null)
        {
            throw new NotFoundException("rider with this id does not exist");
        }

        return riderRepository.save(rider);
    }




    public Rider removeUser(int id) throws NotFoundException {
        Rider findUser = findRiderById(id);
        if(findUser==null)
        {
            throw new NotFoundException("rider does not exists");
        }
        riderRepository.delete(findUser);
        return findUser;
    }

    public Rider loginAsUser(Rider rider) throws InvalidCredentialsException {
        Rider findUser = riderRepository.findByUserPassword(rider.getEmail(), rider.getPassword());
        if (findUser == null)
            throw new InvalidCredentialsException("Invalid Credentials");
        return findUser;
    }


}
