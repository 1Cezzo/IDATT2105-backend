package com.IDATT2105.IDATT2105.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.IDATT2105.IDATT2105.model.User;
import com.IDATT2105.IDATT2105.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User existingUser = userService.getUserByUsername(user.getUsername());
        
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return "Login successful";
        } else {
            return "Invalid username or password";
        }
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        if (userService.getUserByUsername(user.getUsername()) != null) {
            return "Username already exists";
        }
        
        userService.saveUser(user);
        
        return "User registered successfully";
    }

    @GetMapping("/checkUsername")
    public ResponseEntity<String> checkUsername(@RequestParam String username) {
        if (userService.getUserByUsername(username) != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Username already exists");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Username is available");
        }
    }

    @GetMapping("/users")
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserByUsername(@RequestParam String username) {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    
}