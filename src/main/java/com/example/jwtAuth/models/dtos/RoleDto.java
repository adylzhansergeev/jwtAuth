package com.example.jwtAuth.models.dtos;

import com.example.jwtAuth.models.dtos.base.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleDto extends BaseDto {

    private String name;
}
