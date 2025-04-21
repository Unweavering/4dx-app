package com._dx.dto.wig;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class WigGoalRequest {

    @NotBlank(message = "목표 제목은 필수입니다.")
    private String title;

    private String description;

    @NotNull(message = "마감일은 필수입니다.")
    private LocalDate dueDate;
}
