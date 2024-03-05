package com.work.szs.refund.adapter.in;

import com.work.szs.common.annotation.WebAdapter;
import com.work.szs.common.api.response.ApiRes;
import com.work.szs.refund.application.service.CalcRefundUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/szs")
public class RefundController {
    private final CalcRefundUseCase calcRefundUseCase;

    @GetMapping("/refund")
    @Operation(summary = "결정세액 조회", description = "로그인한 사용자의 결정세액을 조회한다.",
            responses = {@ApiResponse(responseCode = "200", description = "조회 성공")})
    public ApiRes<Map<String, String>> calcRefund() {
        return ApiRes.createSuccess(calcRefundUseCase.calcRefund());
    }
}
