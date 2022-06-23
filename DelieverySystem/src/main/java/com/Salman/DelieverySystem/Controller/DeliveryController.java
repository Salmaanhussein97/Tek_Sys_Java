package com.Salman.DelieverySystem.Controller;

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

import com.Salman.DelieverySystem.Model.Delivery;
import com.Salman.DelieverySystem.Service.DeliveryService;



@Controller
@CrossOrigin
@RequestMapping("/user/delivery/")
public class DeliveryController {

	@Autowired
	private DeliveryService deliveryService;

	@GetMapping("getAll")
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(deliveryService.getAllDeliveries());
	}

	@PostMapping("create")
	public ResponseEntity<?> create(@RequestBody Delivery delivery) {
		try {
			return ResponseEntity.ok(deliveryService.addDelivery(delivery));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("update")
	public ResponseEntity<?> update(@RequestBody Delivery delivery) {
		try {
			return ResponseEntity.ok(deliveryService.updateUserInfo(delivery));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
		try {
			return ResponseEntity.ok(deliveryService.removeUser(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("findById/{id}")
	public ResponseEntity<?> findById(@PathVariable(name = "id") int id) {
		try {
			return ResponseEntity.ok(deliveryService.findDeliveryById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("findByUserId/{id}")
	public ResponseEntity<?> findByUserId(@PathVariable(name = "id") int id) {
		try {
			return ResponseEntity.ok(deliveryService.getAllDeliveriesByUserId(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
