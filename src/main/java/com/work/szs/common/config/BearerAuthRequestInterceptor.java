package com.work.szs.common.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.Objects;

public class BearerAuthRequestInterceptor implements RequestInterceptor {
    private String token;

    public BearerAuthRequestInterceptor(String token) {
        Objects.requireNonNull(token);
        this.token = token;
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header("X-API-KEY", token);
    }
}
