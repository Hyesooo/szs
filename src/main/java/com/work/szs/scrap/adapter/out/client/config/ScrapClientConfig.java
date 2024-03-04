package com.work.szs.scrap.adapter.out.client.config;

import com.work.szs.common.config.BearerAuthRequestInterceptor;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@Slf4j
public class ScrapClientConfig {
    @Bean
    public RequestInterceptor apiRequestHeader(@Value("${scrap.x-api-key}") String token) {
        return new BearerAuthRequestInterceptor(token);
    }
}
