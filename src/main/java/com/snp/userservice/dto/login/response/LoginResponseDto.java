package com.snp.userservice.dto.login.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDto {

    private Integer memberIdx;

    private String id;

    private String name;

    private String jwtToken;

}
