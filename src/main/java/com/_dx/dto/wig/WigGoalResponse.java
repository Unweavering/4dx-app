package com._dx.dto.wig;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class WigGoalResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDate dueDate;
}
