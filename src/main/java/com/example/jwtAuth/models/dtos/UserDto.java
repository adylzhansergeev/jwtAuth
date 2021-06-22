package com.example.jwtAuth.models.dtos;

import com.example.jwtAuth.models.dtos.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String login;

    private String password;

    private RoleDto role;
}
