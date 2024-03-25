package com.snp.userservice.service.login;

import com.snp.userservice.dto.api.response.ApiResponseDto;
import com.snp.userservice.dto.login.request.LoginRequestDto;
import com.snp.userservice.jpa.entity.Member;
import com.snp.userservice.jpa.repository.MemberRepository;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private MemberRepository memberRepository;

    @Bean
    private PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Override
    public ApiResponseDto login(LoginRequestDto loginRequestDto) {

        String memberId = loginRequestDto.getId();

        Member member = memberRepository.findByMemId(memberId);

        if(member == null) {
            return ApiResponseDto.builder()
                                 .status(ApiResponseDto.ApiResponseStatus.FAIL)
                                 .message("존재하지 않는 회원입니다.")
                                 .build();
        }

        String password = loginRequestDto.getPassword();
        String memberPassword = member.getPassword();
        BCryptPasswordEncoder passwordEncoder = (BCryptPasswordEncoder) passwordEncoder();

        if(!passwordEncoder.matches(password, memberPassword)) {
            return ApiResponseDto.builder()
                                 .status(ApiResponseDto.ApiResponseStatus.FAIL)
                                 .message("비밀번호가 틀렸습니다.")
                                 .build();
        }

        JwtBuilder JwtToken = Jwts.builder();


        return null;
    }
}
