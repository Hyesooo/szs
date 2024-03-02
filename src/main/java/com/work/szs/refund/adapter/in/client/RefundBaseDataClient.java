package com.work.szs.refund.adapter.in.client;

import com.work.szs.refund.adapter.in.client.config.BaseDataApiRequestConfig;
import com.work.szs.refund.adapter.in.client.resource.BaseDataResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// todo : retry, fallback
@FeignClient(name = "BASE-DATA",
        url = "${base-data.url}",
        configuration = BaseDataApiRequestConfig.class)
public interface RefundBaseDataClient {

    @Operation(summary = "스크래핑", description = "환급액 계산을 위한 기초자료를 스크랩한다.", responses = {
            @ApiResponse(responseCode = "200", description = "스크랩 성공")})
    @GetMapping(value = "/scrap", consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseDataResource scrapBaseData(@RequestParam("name") String name, @RequestParam("regNo") String regNo);

}
