package com.example.findjobbe.controller;

import com.example.findjobbe.model.User;
import com.example.findjobbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/{id}")
    public ResponseEntity<User> findOne(@PathVariable Long id){
        User user = userService.findOne(id);
        if (user!=null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
