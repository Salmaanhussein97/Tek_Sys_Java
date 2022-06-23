package com.Salman.DelieverySystem.Controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Salman.DelieverySystem.Model.Rider;
import com.Salman.DelieverySystem.Model.User;
import com.Salman.DelieverySystem.Repository.RiderRepository;
import com.Salman.DelieverySystem.Service.UserService;

@Controller
@CrossOrigin
@RequestMapping("/home/")
public class HomeController {

	@Autowired
	private UserService userService;
	@Autowired
	private RiderRepository riderRepository;

	@GetMapping("getAll")
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@PostMapping("create")
	public ResponseEntity<?> create(@RequestBody User user) {
		try {
			return ResponseEntity.ok(userService.addNewUser(user));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping("loginUser")
	public ResponseEntity<?> createLogin(@RequestBody User user) {
		try {
			return ResponseEntity.ok(userService.loginAsUser(user));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("update")
	public ResponseEntity<?> update(@RequestBody User user) {
		try {
			return ResponseEntity.ok(userService.updateUserInfo(user));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("findById/{id}")
	public ResponseEntity<?> findById(@PathVariable(name = "id") int id) {
		try {
			return ResponseEntity.ok(userService.findUserById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
		try {
			return ResponseEntity.ok(userService.removeUser(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostConstruct
	public String addRiders()  {
		Rider rider = new Rider();
		rider.setName("Aamir");
		rider.setEmail("aamir@gmail.com");
		rider.setEnabled(true);
		rider.setAddress("makli");
		rider.setPassword("1234");
		rider.setRole("ROLE_RIDER");
		rider.setPhone("78647");
		rider.setAbout("punctual rider");
		rider.setEnabled(true);

		if (riderRepository.findByEmail(rider.getEmail()) == null) {
			riderRepository.save(rider);
		}

		Rider rider1 = new Rider();

		rider1.setName("Aamir");
		rider1.setEmail("amir@gmail.com");
		rider1.setEnabled(true);
		rider1.setAddress("makli");
		rider1.setPassword("1234");
		rider1.setRole("ROLE_RIDER");
		rider1.setPhone("78647");
		rider1.setAbout("punctual rider");
		rider1.setEnabled(true);

		if (riderRepository.findByEmail(rider1.getEmail()) == null) {
			riderRepository.save(rider1);
		}
		return "successfully added riders";
	}

}
