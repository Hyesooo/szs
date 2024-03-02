package com.work.szs.refund.adapter.in.client.config;

import com.work.szs.common.config.BearerAuthRequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class BaseDataApiRequestConfig {
    @Bean
    public RequestInterceptor fooApiRequestHeader(@Value("${base-data.x-api-key}") String token) {
        return new BearerAuthRequestInterceptor(token);
    }
}
