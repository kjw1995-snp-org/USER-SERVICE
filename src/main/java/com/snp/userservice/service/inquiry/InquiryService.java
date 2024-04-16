package com.snp.userservice.service.inquiry;

import com.snp.userservice.dto.api.response.ApiResponseDto;
import com.snp.userservice.dto.inquiry.member.MemberInquiryResponseDto;
import com.snp.userservice.jpa.entity.Member;

import java.util.List;

public interface InquiryService {

    ApiResponseDto<MemberInquiryResponseDto> inquiryMember(int memberIdx);

    ApiResponseDto<List<MemberInquiryResponseDto>> inquiryMembers(List<Integer> memberIdxList);

}
