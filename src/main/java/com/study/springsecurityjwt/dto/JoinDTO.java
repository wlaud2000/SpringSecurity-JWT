package com.study.springsecurityjwt.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JoinDTO {

    private String username;

    private String password;

    @Builder
    public JoinDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
