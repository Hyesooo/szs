package com.work.szs.user.adapter.out.persistence;


import com.work.szs.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);

    List<User> findByName(String name);
}