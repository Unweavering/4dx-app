package com._dx.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter // Jackson이 DTO에서 토큰 get하여 JSON으로 직렬화
@AllArgsConstructor
public class AuthResponse {
    private String token;
}
