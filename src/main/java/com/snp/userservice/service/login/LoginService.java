package com.snp.userservice.service.login;


import com.snp.userservice.dto.api.response.ApiResponseDto;
import com.snp.userservice.dto.login.request.LoginRequestDto;
import com.snp.userservice.dto.login.response.LoginResponseDto;

public interface LoginService {

    ApiResponseDto<LoginResponseDto> login(LoginRequestDto loginRequestDto);

}
