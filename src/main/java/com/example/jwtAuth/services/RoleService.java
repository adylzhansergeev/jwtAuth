package com.example.jwtAuth.services;

import com.example.jwtAuth.exceptions.ServiceException;
import com.example.jwtAuth.models.entities.Role;

import java.util.List;
public interface RoleService {
    List<Role> findAll();
    Role findById(Long id) throws ServiceException;
    Role update(Role role) throws ServiceException;
    Role save(Role role) throws ServiceException ;
    void delete(Role role) throws ServiceException ;
    void deleteById(Long id) throws ServiceException ;
}
