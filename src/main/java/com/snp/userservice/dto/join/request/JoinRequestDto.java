package com.snp.userservice.dto.join.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Builder
public class JoinRequestDto {

    private String id;

    private String password;

    private String name;

    private String gender;

    private String phone;

    private String email;

}
