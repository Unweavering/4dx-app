package com._dx.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

//Swagger는 기본적으로 보안 스키마(Security Scheme) 설정이 있어야 상단에 Authorize 버튼을 생성
@OpenAPIDefinition(
        info = @Info(title = "4DX API", version = "v1", description = "4DX 목표 관리 시스템 API 명세")
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
@Configuration
public class OpenApiConfig {
}
