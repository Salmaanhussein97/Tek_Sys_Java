package com.mytech.postdelivery.service;

import com.mytech.postdelivery.exception.EmptyException;
import com.mytech.postdelivery.exception.InvalidCredentialsException;
import com.mytech.postdelivery.exception.NotFoundException;
import com.mytech.postdelivery.model.User;
import com.mytech.postdelivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.lang.Boolean.TRUE;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public User addNewUser(User user) throws EmptyException, NotFoundException {
        if (user.getEmail()==null)
            throw new EmptyException("Please mention email id");
        if (user.getPassword()==null)
            throw new EmptyException("Please mention strong password");
        if (user.getPhone()==null)
            throw new EmptyException("Please mention contact number");
        if (user.getAddress()==null)
            throw new EmptyException("Please mention address");
        if (user.getName()==null)
            throw new EmptyException("Please mention name");
        if (user.getRole()==null)
            throw new EmptyException("Please mention role");


        User findUser = userRepository.findByEmail(user.getEmail());
        if (findUser!=null)
            throw new EmptyException("User already exists with provided email account");
        user.setEnabled(true);
        return userRepository.save(user);
    }

    public User findUserById(int id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(()-> new NotFoundException("User not found for id = "+id));
    }

    public User updateUserInfo(User user) throws NotFoundException {
        User findUser = findUserById(user.getId());
        if (findUser==null)
        {
            throw new NotFoundException("user does not exist");
        }
        return userRepository.save(user);
    }


    public User removeUser(int id) throws NotFoundException {
        User findUser = findUserById(id);
        if (findUser==null)
        {
            throw new NotFoundException("user does not exist");
        }
        userRepository.delete(findUser);
        return findUser;
    }
    public User loginAsUser(User user) throws InvalidCredentialsException {
        User findUser = userRepository.findByUserPassword(user.getEmail(), user.getPassword());
        if (findUser == null)
            throw new InvalidCredentialsException("Invalid Credentials");
        return findUser;
    }


}
