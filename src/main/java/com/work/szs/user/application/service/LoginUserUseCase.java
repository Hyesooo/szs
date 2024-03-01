package com.work.szs.user.application.service;

import com.work.szs.common.exception.BusinessInvalidValueException;
import com.work.szs.common.util.JwtUtil;
import com.work.szs.user.application.dto.LoginUserRequest;
import com.work.szs.user.application.port.out.LoadUserPort;
import com.work.szs.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginUserUseCase {
    private final LoadUserPort loadUserPort;
    private final BCryptPasswordEncoder encoder;
    @Value("${jwt.token.secret}")
    private String key;

    private static final long EXPIRED_TIME = 1000 * 60 * 60; // 1 hour

    public Map<String, String> login(LoginUserRequest loginUserRequest) {
        User user = loadUserPort.loadUserByUserId(loginUserRequest.getUserId())
                .orElseThrow(() -> new BusinessInvalidValueException("해당하는 사용자ID가 없습니다."));

        user.validatePassword(loginUserRequest.getPassword(), encoder);

        return JwtUtil.createToken(user.getUserId(), key, EXPIRED_TIME);
    }
}
