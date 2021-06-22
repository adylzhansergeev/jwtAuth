package com.example.jwtAuth.services.impl;

import com.example.jwtAuth.exceptions.ServiceException;
import com.example.jwtAuth.models.entities.Role;
import com.example.jwtAuth.repositories.RoleRepository;
import com.example.jwtAuth.services.RoleService;
import com.example.jwtAuth.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public Role findById(Long id) throws ServiceException {
        if (id == null){
            ServiceException.builder()
                    .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                    .message("role not found")
                    .build();
        }
        return roleRepository.findByIdAndDeletedAtIsNull(id);
    }

    @Override
    public Role update(Role role) throws ServiceException {
        if(role.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("role is null")
                    .build();
        }
        return  roleRepository.save(role);
    }

    @Override
    public Role save(Role role) throws ServiceException {
        if(role.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("role already exists")
                    .build();
        }
        return  roleRepository.save(role);
    }

    @Override
    public void delete(Role role) throws ServiceException {
        if(role.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("role is null")
                    .build();
        }
        role = findById(role.getId());
        role.setDeletedAt(new Date());
        roleRepository.save(role);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        Role role = findById(id);
        role.setDeletedAt(new Date());
        roleRepository.save(role);
    }
}
