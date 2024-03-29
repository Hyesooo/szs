package com.work.szs.user.application.service;

import com.work.szs.common.exception.BusinessInvalidValueException;
import com.work.szs.user.application.dto.JoinUserRequest;
import com.work.szs.user.application.port.out.UpdateUserPort;
import com.work.szs.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JoinUserUseCase {
    private final UpdateUserPort updateUserPort;
    private final BCryptPasswordEncoder passwordEncoder;
    private static final Map<String, String> permittedUserMap = Map.of(
            "동탁", "9211081582816",
            "관우", "6811081582816",
            "손권", "8906012455116",
            "유비", "7904111656116",
            "조조", "8103262715702"
    );

    @Transactional
    public void join(JoinUserRequest joinUserRequest) {
        String parsedRegNo = joinUserRequest.getRegNo().replace("-", "");
        validatePermittedUser(joinUserRequest.getName(), parsedRegNo);

        joinUserRequest.setPassword(passwordEncoder.encode(joinUserRequest.getPassword()));

        User user = User.of(joinUserRequest.getUserId(), joinUserRequest.getPassword(), joinUserRequest.getName(), parsedRegNo);
        updateUserPort.joinUser(user);
    }

    private void validatePermittedUser(String name, String regNo) {
        if (!permittedUserMap.containsKey(name)) {
            throw new BusinessInvalidValueException("허가된 사용자가 아닙니다.");
        } else if (!permittedUserMap.get(name).equals(regNo)) {
            throw new BusinessInvalidValueException("주민번호를 확인해주세요.");
        }
    }

}
