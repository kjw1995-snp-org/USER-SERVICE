package com.snp.userservice.common.token;


import com.snp.userservice.jpa.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class TokenProvider implements TokenProvide {
    
    // 비공개 Claims 등록을 위한 KEY 설정
    private static final String AUTH_KEY = "auth";

    private final long accessTokenExpiryMilliseconds;

    private final long refreshTokenExpiryMilliseconds;

    private Key key;

    public TokenProvider(
            @Value("${jwt.secret-key}") String secretKey,
            @Value("${jwt.access-token-expiry-seconds}") long accessTokenExpirySeconds,
            @Value("${jwt.refresh-token-expiry-seconds}") long refreshTokenExpirySeconds) {

        this.accessTokenExpiryMilliseconds = accessTokenExpirySeconds * 1000;
        this.refreshTokenExpiryMilliseconds = refreshTokenExpirySeconds * 1000;
        byte[] keyBytes = Decoders.BASE64URL.decode(secretKey);
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
                   .setHeader(createHeader())
                   .setClaims(claims)
                   .signWith(key, SignatureAlgorithm.HS512)
                   .compact();

    }

    private Map<String, Object> createHeader() {

        Map<String, Object> header = new HashMap<>();

        header.put("typ", "JWT");
        header.put("alg", "HS512");

        return header;
    }

}
