package com.doosan.ddxp.api.core.config.security;


public interface AuthToken<T> {
    boolean validate();
    T getData();
}
