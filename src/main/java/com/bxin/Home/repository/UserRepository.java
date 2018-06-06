package com.bxin.Home.repository;

import com.bxin.Home.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccountAndPasswordAndDeleted(String account, String password, Boolean deleted);

    Optional<User> findByAccountAndDeleted(String account, Boolean deleted);
}
