package com.doosan.ddxp.api.item.web;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doosan.ddxp.api.core.config.redis.GsonRedisSerializer;
import com.doosan.ddxp.api.core.config.security.JwtAuthToken;
import com.doosan.ddxp.api.core.config.security.JwtAuthTokenProvider;

@Controller
public class LoginController {

	@Autowired
	private StringRedisTemplate redisTemplate;
	
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@ResponseBody
	@GetMapping(path = "/login")
	public String login() {
		
		logger.info("122222222 ");
		JwtAuthTokenProvider jwtAuthTokenProvider= new JwtAuthTokenProvider();
		logger.info("33333333 ");
		JwtAuthToken jwtToken = jwtAuthTokenProvider.createLoginAuthToken();
		logger.info("TOKEN_VALUE :"+jwtToken.getToken());
		System.out.println("TOKEN_VALUE :"+jwtToken.getToken());
		redisTemplate.setKeySerializer(new GsonRedisSerializer());
		redisTemplate.setValueSerializer(new GsonRedisSerializer());

		
		redisTemplate.opsForValue().set(jwtToken.getToken(), "abc");
		redisTemplate.expire(jwtToken.getToken(), 1,TimeUnit.HOURS);
		
		logger.info("TOKEN_VALUE :"+jwtToken.getToken());
		
		String result = redisTemplate.opsForValue().get(jwtToken.getToken());
	
		logger.info("AFTER_REDIS : "+result);
		
		return "login!!";
		
	}
}
