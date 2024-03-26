package com.snp.userservice.common.jwt;


import com.snp.userservice.jpa.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class JwtTokenProvideServiceImpl implements JwtTokenProvideService,InitializingBean {
    
    // 비공개 Claims 등록을 위한 KEY 설정
    private static final String AUTH_KEY = "auth";

    private final String secret;

    private final long accessTokenExpiryMilliseconds;

    private final long refreshTokenExpiryMilliseconds;

    private Key key;

    public JwtTokenProvideServiceImpl(
            @Value("${spring.jwt.secret-key}") String secret,
            @Value("${spring.jwt.access-token-expiry-seconds}") long accessTokenExpirySeconds,
            @Value("${spring.jwt.refresh-token-expiry-seconds}") long refreshTokenExpirySeconds) {

        this.secret = secret;
        this.accessTokenExpiryMilliseconds = accessTokenExpirySeconds * 1000;
        this.refreshTokenExpiryMilliseconds = refreshTokenExpirySeconds * 1000;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String createAccessToken(Member member) {

        long now = new Date().getTime();
        Date expiryDate = new Date(now + accessTokenExpiryMilliseconds);

        Map<String, Object> privateClaimsMap = new HashMap<>();
        privateClaimsMap.put(AUTH_KEY, "USER");

        Claims claims = Jwts.claims(privateClaimsMap).setAudience(member.getName()).setExpiration(expiryDate);

        return Jwts.builder()
                   .setClaims(claims)
                   .signWith(key, SignatureAlgorithm.HS512)
                   .compact();

    }

}
