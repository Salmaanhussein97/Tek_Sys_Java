package com.Salman.DelieverySystem.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Salman.DelieverySystem.Model.Rider;
import com.Salman.DelieverySystem.Repository.RiderRepository;

@Service
public class RiderService {

	@Autowired
	private RiderRepository riderRepository;

	public List<Rider> getAllUsers() {
		return riderRepository.findAll();
	}

	@Transactional
	public Rider addNewRider(Rider rider) {

		Rider findRider = riderRepository.findByEmail(rider.getEmail());
		rider.setEnabled(true);
		return riderRepository.save(rider);
	}

	public Rider findRiderById(int id) {
		return riderRepository.findById(id).orElseThrow(null);
	}

	public Rider updateRiderInfo(Rider rider) {
		Rider findUser = findRiderById(rider.getId());

		return riderRepository.save(rider);
	}

	public Rider removeUser(int id) {
		Rider findUser = findRiderById(id);
		riderRepository.delete(findUser);
		return findUser;
	}

	public Rider loginAsUser(Rider rider) {
		Rider findUser = riderRepository.findByUserPassword(rider.getEmail(), rider.getPassword());

		return findUser;
	}

}
