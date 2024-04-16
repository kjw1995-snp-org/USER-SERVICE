package com.snp.userservice.controller.inquiry;

import com.snp.userservice.dto.api.response.ApiResponseDto;
import com.snp.userservice.dto.inquiry.member.MemberInquiryResponseDto;
import com.snp.userservice.global.GlobalUrl;
import com.snp.userservice.service.inquiry.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    @GetMapping(GlobalUrl.INQUIRY_MEMBER + "/{memberIdx}")
    public ApiResponseDto<MemberInquiryResponseDto> inquiryMember(@PathVariable("memberIdx") int memberIdx) {
        return inquiryService.inquiryMember(memberIdx);
    }

    @PostMapping(GlobalUrl.INQUIRY_MEMBERS)
    @ResponseBody
    public ApiResponseDto<List<MemberInquiryResponseDto>> inquiryMembers(@RequestBody List<Integer> memberIdxList) {
        return inquiryService.inquiryMembers(memberIdxList);
    }

}
