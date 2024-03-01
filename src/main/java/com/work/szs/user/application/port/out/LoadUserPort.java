package com.work.szs.user.application.port.out;

import com.work.szs.user.domain.User;

import java.util.Optional;

public interface LoadUserPort {
    Optional<User> loadUserByUserId(String userId);

    Optional<User> loadUserById(Long id);
}
