package com.daila.yousty.repository;

import com.daila.yousty.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserById(Long id);
    User getByEmail(String email);
    User getByUsername(String username);
}