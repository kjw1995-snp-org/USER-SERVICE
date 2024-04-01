package com.snp.userservice.common.token;

import com.snp.userservice.jpa.entity.Member;

public interface TokenProvide {

    String createAccessToken(Member member);

}
