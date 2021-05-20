package com.doosan.ddxp.api.core.config.security;

public interface AuthTokenProvider<T> {
	//T createLoginAuthToken(String id);
	T createLoginAuthToken();
    T convertAuthToken(String token);
}
