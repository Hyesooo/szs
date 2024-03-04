package com.work.szs.scrap.adapter.out.client;

import com.work.szs.scrap.adapter.out.client.config.ScrapClientConfig;
import com.work.szs.scrap.adapter.out.client.dto.ScrapDataResponse;
import com.work.szs.scrap.adapter.out.client.dto.ScrapResult;
import com.work.szs.scrap.application.dto.request.ScrapDataRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// todo : retry, fallback
@FeignClient(name = "SCRAP",
        url = "${scrap.url}",
        configuration = ScrapClientConfig.class)
public interface ScrapClient {

    @Operation(summary = "스크래핑", description = "환급액 계산을 위한 기초자료를 스크랩한다.", responses = {
            @ApiResponse(responseCode = "200", description = "스크랩 성공")})
    @PostMapping(value = "/scrap", consumes = MediaType.APPLICATION_JSON_VALUE)
    ScrapDataResponse<ScrapResult> scrapBaseInfo(@RequestBody @Valid ScrapDataRequest request);
}
