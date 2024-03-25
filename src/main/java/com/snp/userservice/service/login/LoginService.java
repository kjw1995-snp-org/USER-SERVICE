package com.snp.userservice.service.login;


import com.snp.userservice.dto.api.response.ApiResponseDto;
import com.snp.userservice.dto.login.request.LoginRequestDto;

public interface LoginService {

    ApiResponseDto login(LoginRequestDto loginRequestDto);

}
