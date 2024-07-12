package com.hanghae99.preonboardingbackend.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SigninReqDto {

    private String username;
    private String password;
}
