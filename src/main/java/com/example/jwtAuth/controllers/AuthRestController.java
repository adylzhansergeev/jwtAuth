package com.example.jwtAuth.controllers;

import com.example.jwtAuth.exceptions.ServiceException;
import com.example.jwtAuth.models.dtos.UserDto;
import com.example.jwtAuth.models.entities.Role;
import com.example.jwtAuth.models.entities.User;
import com.example.jwtAuth.models.mappers.UserMapper;
import com.example.jwtAuth.models.userDetailsImpl.UserDetailsImpl;
import com.example.jwtAuth.services.UserService;
import com.example.jwtAuth.shared.security.jwt.JwtUtils;
import com.example.jwtAuth.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthRestController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;
    private UserMapper userMapper;

    @Autowired
    public AuthRestController(UserService userService, AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserMapper userMapper){
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userMapper = userMapper;
    }
    @PostMapping("sign-in")
    public ResponseEntity<?> login(@RequestBody UserDto userDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                userDto.getLogin(),
                userDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return new ResponseEntity<>( jwt , HttpStatus.OK);
    }
    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) throws ServiceException {
        User user = userMapper.toEntity(userDto);
        if (userService.findByLogin(user.getLogin()) != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("login already exists")
                    .build();
        }
        Role role = new Role();
        role.setId(2L);
        user.setRole(role);
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }
}
