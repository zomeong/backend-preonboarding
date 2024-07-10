package com.hanghae99.preonboardingbackend.model.service;

import com.hanghae99.preonboardingbackend.model.dto.SignupReqDto;
import com.hanghae99.preonboardingbackend.model.dto.SignupResDto;
import com.hanghae99.preonboardingbackend.model.entity.Authority;
import com.hanghae99.preonboardingbackend.model.entity.User;
import com.hanghae99.preonboardingbackend.model.repository.AuthorityRepository;
import com.hanghae99.preonboardingbackend.model.repository.UserRepository;
import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SignupResDto signup(SignupReqDto userReqDto) {

        if(userRepository.findByUsername(userReqDto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        User user = User.builder()
            .username(userReqDto.getUsername())
            .password(passwordEncoder.encode(userReqDto.getPassword()))
            .nickname(userReqDto.getNickname())
            .authorities(new HashSet<>())
            .build();
        userRepository.save(user);

        Authority authority = findAuthority("ROLE_USER");
        user.getAuthorities().add(authority);

        return user.toResponseDto();
    }

    private Authority findAuthority(String authorityName) {
        return authorityRepository.findByAuthorityName(authorityName).orElseGet(() ->
            authorityRepository.save(Authority.builder().authorityName(authorityName).build()));
    }
}