package com.example.jwtAuth.controllers;

import com.example.jwtAuth.models.mappers.UserMapper;
import com.example.jwtAuth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users")
public class UserRestController {
    private UserService userService;
    private UserMapper userMapper;
    @Autowired
    public UserRestController(UserService userService, UserMapper userMapper){
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(userMapper.toDtoList(userService.findAll()), HttpStatus.OK);
    }
}
