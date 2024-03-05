package com.work.szs.common.config;

import com.work.szs.common.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TokenService {
    public TokenDto getCurrentTokenInfo() {
        TokenDto result = new TokenDto();

        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        result.setUserId((String) requestAttributes.getAttribute("userId", 0));

        return result;
    }
}
