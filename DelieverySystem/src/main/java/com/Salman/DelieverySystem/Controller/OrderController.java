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

import com.Salman.DelieverySystem.Model.Order;
import com.Salman.DelieverySystem.Service.OrderService;



@Controller
@CrossOrigin
@RequestMapping("/user/order/")
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    @GetMapping("getAll")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody Order order){
        try{
            return ResponseEntity.ok(orderService.addOrder(order));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody Order order){
        try{
            return ResponseEntity.ok(orderService.updateUserInfo(order));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable( name= "id") int id){
        try{
            return ResponseEntity.ok(orderService.removeUser(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<?> findById(@PathVariable( name= "id") int id){
        try{
            return ResponseEntity.ok(orderService.findOrderById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }}

    @GetMapping("findByUserId/{id}")
    public ResponseEntity<?> findByUserId(@PathVariable( name= "id") int id){
        try{
            return ResponseEntity.ok(orderService.getAllOrdersByUserId(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
