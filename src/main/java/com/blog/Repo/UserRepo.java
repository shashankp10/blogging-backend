package com.blog.Repo;

import com.blog.Entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserAuth, Integer> {
    Optional<UserAuth> findByEmail(String email);
    boolean existsByEmail(String email);
}
