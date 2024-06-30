package com.study.springsecurityjwt.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginDTO {

    String username;

    String password;

    @Builder
    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
