package com.work.szs.refund.application.service;

import com.work.szs.common.config.TokenService;
import com.work.szs.common.exception.BusinessInvalidValueException;
import com.work.szs.refund.application.port.persistence.LoadRefundResultPort;
import com.work.szs.scrap.domain.RefundResult;
import com.work.szs.user.application.port.out.LoadUserPort;
import com.work.szs.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CalcRefundUseCase {
    private final TokenService tokenService;
    private final LoadUserPort loadUserPort;
    private final LoadRefundResultPort loadRefundResultPort;

    public Map<String, String> calcRefund() {
        String id = tokenService.getCurrentTokenInfo().getUserId();
        User user = loadUserPort.loadUserByUserId(id).orElseThrow(()
                -> new BusinessInvalidValueException("유저정보를 확인해주세요.")
        );

        RefundResult result = loadRefundResultPort.loadResultByUser(user).orElseThrow(
                () -> new BusinessInvalidValueException("계산을 위한 기초정보가 없습니다.\n 스크래핑 먼저 진행해주세요.")
        );

        return Map.of("결정세액", String.format("%,d", result.calc()));
    }
}
