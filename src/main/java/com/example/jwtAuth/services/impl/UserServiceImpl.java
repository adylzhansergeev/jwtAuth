package com.example.jwtAuth.services.impl;

import com.example.jwtAuth.exceptions.ServiceException;
import com.example.jwtAuth.models.entities.User;
import com.example.jwtAuth.models.userDetailsImpl.UserDetailsImpl;
import com.example.jwtAuth.repositories.UserRepository;
import com.example.jwtAuth.services.UserService;
import com.example.jwtAuth.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public User findById(Long id) throws ServiceException {
        if (id == null){
            ServiceException.builder()
                    .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                    .message("user not found")
                    .build();
        }
        return userRepository.findByIdAndDeletedAtIsNull(id);
    }

    @Override
    public User update(User user) throws ServiceException {
        if(user.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("user is null")
                    .build();
        }
        return  userRepository.save(user);
    }

    @Override
    public User save(User user) throws ServiceException {
        if(user.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("user already exists")
                    .build();
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return  userRepository.save(user);
    }

    @Override
    public void delete(User user) throws ServiceException {
        if(user.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("user is null")
                    .build();
        }
        user = findById(user.getId());
        user.setDeletedAt(new Date());
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        User user = findById(id);
        user.setDeletedAt(new Date());
        userRepository.save(user);
    }

    @Override
    public User findByLogin(String login) throws ServiceException {
        return userRepository.findByLoginAndDeletedAtIsNull(login);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByLoginAndDeletedAtIsNull(s);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return UserDetailsImpl.build(user);
    }
}
