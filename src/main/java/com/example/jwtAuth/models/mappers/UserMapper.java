package com.example.jwtAuth.models.mappers;

import com.example.jwtAuth.models.dtos.UserDto;
import com.example.jwtAuth.models.entities.User;
import com.example.jwtAuth.shared.utils.mappers.SuperMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper implements SuperMapper<User, UserDto> {

    private ModelMapper modelMapper;
    private RoleMapper roleMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper, RoleMapper roleMapper){
        this.modelMapper = modelMapper;
        this.roleMapper = roleMapper;
    }
    @Override
    public UserDto toDto(User user) {
        UserDto userDto = modelMapper.map(user,UserDto.class);
        if (user.getRole() != null){
            userDto.setRole(roleMapper.toDto(user.getRole()));
        }
        return userDto;
    }

    @Override
    public User toEntity(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        if (userDto.getRole() != null){
            user.setRole(roleMapper.toEntity(userDto.getRole()));
        }
        return user;
    }

    @Override
    public List<UserDto> toDtoList(List<User> users) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : users) {
            userDtoList.add(toDto(user));
        }
        return userDtoList;
    }

    @Override
    public List<User> toEntityList(List<UserDto> eList) {
        List<User> userList = new ArrayList<>();
        for (UserDto userDto : eList) {
            userList.add(toEntity(userDto));
        }
        return userList;
    }
}
