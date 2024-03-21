package com.snp.userservice.service.join;

import com.snp.userservice.dto.api.response.ApiResponseDto;
import com.snp.userservice.dto.join.request.JoinRequestDto;
import com.snp.userservice.jpa.entity.Member;
import com.snp.userservice.jpa.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JoinServiceImpl implements JoinService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public ApiResponseDto joinProgress(JoinRequestDto requestDto) {
        try {

            logger.info("회원가입 요청 정보 = {}", requestDto);

            memberRepository.save(
                    Member.builder()
                            .memId(requestDto.getId())
                            .password(requestDto.getPassword())
                            .gender(requestDto.getGender())
                            .name(requestDto.getName())
                            .phone(requestDto.getPhone())
                            .email(requestDto.getEmail())
                            .build()
            );

            return ApiResponseDto.builder()
                                 .status(ApiResponseDto.ApiResponseStatus.SUC)
                                 .message("회원가입이 완료되었습니다.")
                                 .build();

        } catch(Exception e) {
            logger.info("회원가입 실패 오류 발생, message = {}", e.getMessage());
            return ApiResponseDto.builder()
                                 .status(ApiResponseDto.ApiResponseStatus.ERR)
                                 .message("회원가입 실패")
                                 .build();
        }

    }
}
