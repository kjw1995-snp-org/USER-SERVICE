package com.snp.userservice.service.login;

import com.snp.userservice.common.token.TokenProvide;
import com.snp.userservice.dto.api.request.ApiRequestDto;
import com.snp.userservice.dto.api.response.ApiResponseDto;
import com.snp.userservice.dto.login.request.LoginRequestDto;
import com.snp.userservice.dto.login.response.LoginResponseDto;
import com.snp.userservice.jpa.entity.Member;
import com.snp.userservice.jpa.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TokenProvide jwtTokenProvideService;

    @Bean
    private PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Override
    public ApiResponseDto<Object> login(ApiRequestDto<LoginRequestDto> loginRequestDto) {

        String memberId = loginRequestDto.getData().getId();

        Member member = memberRepository.findByMemId(memberId);

        if(member == null) {
            return ApiResponseDto.builder()
                                 .status(ApiResponseDto.ApiResponseStatus.FAIL)
                                 .message("존재하지 않는 회원입니다.")
                                 .build();
        }

        String password = loginRequestDto.getData().getPassword();
        String memberPassword = member.getPassword();
        BCryptPasswordEncoder passwordEncoder = (BCryptPasswordEncoder) passwordEncoder();

        if(!passwordEncoder.matches(password, memberPassword)) {

            return ApiResponseDto.builder()
                                 .status(ApiResponseDto.ApiResponseStatus.FAIL)
                                 .message("비밀번호가 틀렸습니다.")
                                 .build();
        }

        String jwtToken = jwtTokenProvideService.createAccessToken(member);
        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                                                            .id(member.getMemId())
                                                            .name(member.getName())
                                                            .build();

        return ApiResponseDto.builder()
                             .status(ApiResponseDto.ApiResponseStatus.SUC)
                             .jwtToken(jwtToken)
                             .data(loginResponseDto)
                             .message("로그인 성공")
                             .build();

    }
}
