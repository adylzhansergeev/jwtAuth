package com.example.jwtAuth.exceptions;

import com.example.jwtAuth.shared.utils.codes.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceException extends Exception{

    protected String message;
    protected ErrorCode errorCode;

}