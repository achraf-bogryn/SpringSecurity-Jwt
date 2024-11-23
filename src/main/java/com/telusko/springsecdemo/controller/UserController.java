package com.telusko.springsecdemo.controller;

import com.telusko.springsecdemo.model.User;
//import com.telusko.springsecdemo.service.Jwt;
import com.telusko.springsecdemo.service.JwtService;
import com.telusko.springsecdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("register")
    public void addUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
       return userService.getAll();
    }
@PostMapping("login")
    public String login(@RequestBody User user) {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),
                        user.getPassword()));

        if(authenticate.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        }
        else
            return "Failed to login";
    }
}
