package com.hanghae99.preonboardingbackend.model.repository;

import com.hanghae99.preonboardingbackend.model.entity.Authority;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {

    Optional<Authority> findByAuthorityName(String authorityName);
}
