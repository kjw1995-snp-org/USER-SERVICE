package com.snp.userservice.config.security;

import com.snp.userservice.global.GlobalUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests().anyRequest().permitAll();
        http.csrf().ignoringAntMatchers(GlobalUrl.JOIN_PROGRESS + "/**");
        return http.build();

    }

}
