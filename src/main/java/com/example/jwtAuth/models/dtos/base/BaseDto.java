package com.example.jwtAuth.models.dtos.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDto {

    private Long id;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;
}
