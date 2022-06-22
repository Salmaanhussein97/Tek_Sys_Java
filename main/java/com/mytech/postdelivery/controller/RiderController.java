package com.mytech.postdelivery.controller;

import com.mytech.postdelivery.exception.DuplicateException;
import com.mytech.postdelivery.model.Rider;
import com.mytech.postdelivery.model.User;
import com.mytech.postdelivery.repository.RiderRepository;
import com.mytech.postdelivery.service.RiderService;
import com.mytech.postdelivery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@CrossOrigin
@RequestMapping("/rider/")
public class RiderController {
    @Autowired
    private RiderService riderService;

    @GetMapping("getAll")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(riderService.getAllUsers());
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody Rider rider){
        try{
            return ResponseEntity.ok(riderService.addNewRider(rider));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody Rider rider){
        try{
            return ResponseEntity.ok(riderService.updateRiderInfo(rider));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping("findById/{id}")
    public ResponseEntity<?> findById(@PathVariable( name= "id") int id){
        try{
            return ResponseEntity.ok(riderService.findRiderById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable( name= "id") int id){
        try{
            return ResponseEntity.ok(riderService.removeUser(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("loginUser")
    public ResponseEntity<?> createLogin(@RequestBody Rider rider){
        try{
            return ResponseEntity.ok(riderService.loginAsUser(rider));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
