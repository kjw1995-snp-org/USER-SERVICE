package com.snp.userservice.service.login;


import com.snp.userservice.dto.api.request.ApiRequestDto;
import com.snp.userservice.dto.api.response.ApiResponseDto;
import com.snp.userservice.dto.login.request.LoginRequestDto;

public interface LoginService {

    ApiResponseDto<Object> login(ApiRequestDto<LoginRequestDto> loginRequestDto);

}
