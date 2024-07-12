package com.hanghae99.preonboardingbackend.model.controller;

import com.hanghae99.preonboardingbackend.model.dto.SigninReqDto;
import com.hanghae99.preonboardingbackend.model.dto.SignupReqDto;
import com.hanghae99.preonboardingbackend.model.dto.SignupResDto;
import com.hanghae99.preonboardingbackend.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResDto> signup(@RequestBody SignupReqDto signupReqDto) {
        return ResponseEntity.ok(userService.signup(signupReqDto));
    }
}
