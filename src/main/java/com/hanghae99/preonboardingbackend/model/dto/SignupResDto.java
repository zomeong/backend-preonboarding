package com.hanghae99.preonboardingbackend.model.dto;

import com.hanghae99.preonboardingbackend.model.entity.Authority;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupResDto {

    private String username;
    private String nickname;
    private Set<Authority> authorities;
}
