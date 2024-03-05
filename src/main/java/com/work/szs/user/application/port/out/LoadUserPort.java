package com.work.szs.user.application.port.out;

import com.work.szs.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface LoadUserPort {
    Optional<User> loadUserByUserId(String userId);

    List<User> loadUserByName(String name);

    Optional<User> loadUserById(Long id);
}
