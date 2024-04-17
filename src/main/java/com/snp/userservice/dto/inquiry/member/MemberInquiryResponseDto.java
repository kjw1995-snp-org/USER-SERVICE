package com.snp.userservice.dto.inquiry.member;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class MemberInquiryResponseDto {

    private Integer memberIdx;

    private String memberId;

    private String memberName;

}
