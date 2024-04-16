package com.snp.userservice.service.inquiry;

import com.snp.userservice.dto.api.response.ApiResponseDto;
import com.snp.userservice.dto.inquiry.member.MemberInquiryResponseDto;
import com.snp.userservice.jpa.entity.Member;
import com.snp.userservice.jpa.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InquiryServiceImpl implements InquiryService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public ApiResponseDto<MemberInquiryResponseDto> inquiryMember(int memberIdx) {

        Member member = memberRepository.findById(memberIdx).get();

        MemberInquiryResponseDto memberInquiryResponseDto = MemberInquiryResponseDto.builder()
                .memberId(member.getMemId())
                .memberName(member.getName())
                .build();

        ApiResponseDto<MemberInquiryResponseDto> response = ApiResponseDto.<MemberInquiryResponseDto>builder()
                .status(ApiResponseDto.ApiResponseStatus.SUC)
                .message("회원 정보 조회 성공")
                .data(memberInquiryResponseDto)
                .build();

        return response;
    }

    @Override
    public ApiResponseDto<List<MemberInquiryResponseDto>> inquiryMembers(List<Integer> memberIdxList) {

        List<Member> memberList = memberRepository.findAllById(memberIdxList);

        List<MemberInquiryResponseDto> memberInquiryResponseDtoList = new ArrayList<>();

        for (Member member : memberList) {
            MemberInquiryResponseDto memberInquiryResponseDto = MemberInquiryResponseDto.builder()
                    .memberId(member.getMemId())
                    .memberName(member.getName())
                    .build();

            memberInquiryResponseDtoList.add(memberInquiryResponseDto);

        }

        ApiResponseDto<List<MemberInquiryResponseDto>> response = ApiResponseDto.<List<MemberInquiryResponseDto>>builder()
                .status(ApiResponseDto.ApiResponseStatus.SUC)
                .message("회원 정보 리스트 조회 성공")
                .data(memberInquiryResponseDtoList)
                .build();

        return response;
    }
}
