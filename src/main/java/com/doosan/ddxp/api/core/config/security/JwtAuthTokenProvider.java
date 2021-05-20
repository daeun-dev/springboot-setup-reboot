package com.doosan.ddxp.api.core.config.security;

import java.security.Key;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthTokenProvider implements AuthTokenProvider<JwtAuthToken> {

    private final Key key;
    
    @Value("${jwt.secret}")
    private String secret;
    
    @Value("${jwt.login.expiredDate}")
    private int loginExpiredDate;

    public JwtAuthTokenProvider() {
    	logger.info("444444444 "+secret);
		this.key = Keys.hmacShaKeyFor(secret.getBytes());
		logger.info("555555 : "+this.key);
    }

    Logger logger = LoggerFactory.getLogger(JwtAuthToken.class);
/*    @Override
    public JwtAuthToken createLoginAuthToken(String id) {
    	
    	//권한 조회하는 로직
    	this.role = Role.MASTER;
        return new JwtAuthToken("doosan.ddxp.login", id, new Date(System.currentTimeMillis() + loginExpiredDate), key, role);
    } */

    @Override
    public JwtAuthToken createLoginAuthToken() {
    	logger.info("hello");
    	return new JwtAuthToken(key);
    }
    
    @Override
    public JwtAuthToken convertAuthToken(String token) {
        return new JwtAuthToken(token, key);
    }

}