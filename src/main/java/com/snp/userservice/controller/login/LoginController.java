package com.snp.userservice.controller.login;

import com.snp.userservice.dto.api.response.ApiResponseDto;
import com.snp.userservice.dto.login.request.LoginRequestDto;
import com.snp.userservice.dto.login.response.LoginResponseDto;
import com.snp.userservice.global.GlobalUrl;
import com.snp.userservice.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping(GlobalUrl.LOGIN_ACTION)
    @ResponseBody
    public ApiResponseDto<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) { return loginService.login(loginRequestDto); }

}
