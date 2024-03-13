package com.snp.userservice.controller;

import com.snp.userservice.global.GlobalUrl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @PostMapping(GlobalUrl.LOGIN_PROGRESS)
    public void progressLogin() {

    }

}
