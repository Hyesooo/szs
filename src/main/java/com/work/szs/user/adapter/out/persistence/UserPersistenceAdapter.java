package com.work.szs.user.adapter.out.persistence;

import com.work.szs.common.annotation.PersistenceAdapter;
import com.work.szs.user.application.port.out.LoadUserPort;
import com.work.szs.user.application.port.out.UpdateUserPort;
import com.work.szs.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@PersistenceAdapter
@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UpdateUserPort, LoadUserPort {

    private final UserRepository userRepository;

    @Override
    public void joinUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> loadUserByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }
}