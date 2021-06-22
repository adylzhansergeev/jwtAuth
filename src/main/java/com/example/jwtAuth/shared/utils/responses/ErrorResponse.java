package com.example.jwtAuth.shared.utils.responses;

import com.example.jwtAuth.shared.utils.codes.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    @JsonProperty("status")
    private String status = "error";

    @JsonProperty("errorCode")
    private ErrorCode code;

    @JsonProperty("message")
    private String message;
}
