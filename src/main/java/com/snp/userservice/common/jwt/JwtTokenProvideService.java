package com.snp.userservice.common.jwt;

import com.snp.userservice.jpa.entity.Member;

public interface JwtTokenProvideService {

    String createAccessToken(Member member);

}
