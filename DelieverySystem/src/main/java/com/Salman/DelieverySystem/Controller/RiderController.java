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

import com.Salman.DelieverySystem.Model.Rider;
import com.Salman.DelieverySystem.Service.RiderService;



@Controller
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

