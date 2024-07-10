package com.hanghae99.preonboardingbackend.model.repository;

import com.hanghae99.preonboardingbackend.model.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByUsername(String username);
}
