package com.example.jwtAuth.models.mappers;

import com.example.jwtAuth.models.dtos.RoleDto;
import com.example.jwtAuth.models.entities.Role;
import com.example.jwtAuth.shared.utils.mappers.SuperMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class RoleMapper implements SuperMapper<Role, RoleDto> {

    private ModelMapper modelMapper;
    @Autowired
    public RoleMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }
    @Override
    public RoleDto toDto(Role role) {
        return Objects.isNull(role) ? null : modelMapper.map(role, RoleDto.class);
    }

    @Override
    public Role toEntity(RoleDto roleDto) {
        return Objects.isNull(roleDto) ? null : modelMapper.map(roleDto, Role.class);
    }

    @Override
    public List<RoleDto> toDtoList(List<Role> roles) {
        List<RoleDto> roleDtoList = new ArrayList<>();
        for (Role role: roles) {
            roleDtoList.add(toDto(role));
        }
        return roleDtoList;
    }

    @Override
    public List<Role> toEntityList(List<RoleDto> eList) {
        List<Role> roleList = new ArrayList<>();
        for (RoleDto roleDto: eList) {
            roleList.add(toEntity(roleDto));
        }
        return roleList;
    }
}
