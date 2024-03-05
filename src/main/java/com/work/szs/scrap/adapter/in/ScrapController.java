package com.work.szs.scrap.adapter.in;

import com.work.szs.common.annotation.WebAdapter;
import com.work.szs.common.api.response.ApiRes;
import com.work.szs.scrap.application.service.ScrapDataUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/szs")
public class ScrapController {
    private final ScrapDataUseCase scrapDataUseCase;

    @PostMapping("/scrap")
    @Operation(summary = "스크래핑", description = "계산 기초 정보 조회를 위한 스크래핑을 한다.", responses = {
            @ApiResponse(responseCode = "200", description = "스크래핑 성공")})
    public ApiRes<?> scrap() {
        scrapDataUseCase.inputBaseData();
        return ApiRes.createSuccessWithNoContent();
    }
}