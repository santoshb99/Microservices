package com.testlab.user.service.controller;

import com.testlab.user.service.entity.Users;
import com.testlab.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // create
    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users users){

        Users user1 = userService.saveUser(users);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    //Get single user

    @GetMapping("/{userId}")
    public ResponseEntity<Users> getSingleUser(@PathVariable String userId){
        Users user =  userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    //Get all users

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers(){
        List<Users> allUser =  userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }
}
