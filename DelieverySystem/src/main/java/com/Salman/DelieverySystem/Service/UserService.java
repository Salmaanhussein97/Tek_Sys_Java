package com.Salman.DelieverySystem.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Salman.DelieverySystem.Model.User;
import com.Salman.DelieverySystem.Repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Transactional
	public User addNewUser(User user) {

		User findUser = userRepository.findByEmail(user.getEmail());

		user.setEnabled(true);
		return userRepository.save(user);
	}

	public User findUserById(int id) {
		return userRepository.findById(id).orElseThrow(null);
	}

	public User updateUserInfo(User user) {
		User findUser = findUserById(user.getId());
		return userRepository.save(user);
	}

	public User removeUser(int id) {
		User findUser = findUserById(id);

		userRepository.delete(findUser);
		return findUser;
	}

	public User loginAsUser(User user) {
		User findUser = userRepository.findByUserPassword(user.getEmail(), user.getPassword());

		return findUser;
	}

}
