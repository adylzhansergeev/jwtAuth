package com.example.jwtAuth.services;

import com.example.jwtAuth.exceptions.ServiceException;
import com.example.jwtAuth.models.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
public interface UserService extends UserDetailsService {
    List<User> findAll();
    User findById(Long id) throws ServiceException;
    User findByLogin(String login) throws ServiceException;
    User update(User user) throws ServiceException ;
    User save(User user) throws ServiceException ;
    void delete(User user) throws ServiceException ;
    void deleteById(Long id) throws ServiceException ;
}
