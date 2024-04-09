package com.snp.userservice.dto.api.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDto<T> {

    public enum ApiResponseStatus {
        SUC, FAIL, ERR
    }

    private ApiResponseStatus status;

    private T data;

    private String message;

}
