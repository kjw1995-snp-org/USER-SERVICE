package com.snp.userservice.controller.join;

import com.snp.userservice.dto.api.response.ApiResponseDto;
import com.snp.userservice.dto.join.request.JoinRequestDto;
import com.snp.userservice.global.GlobalUrl;
import com.snp.userservice.service.join.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JoinController {

    @Autowired
    private JoinService joinService;

    @PostMapping(GlobalUrl.JOIN_PROGRESS)
    @ResponseBody
    public ApiResponseDto joinProgress(@RequestBody JoinRequestDto requestDto) { return joinService.joinProgress(requestDto); }

}
