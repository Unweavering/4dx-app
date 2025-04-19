package com._dx.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wig_goals")
@Getter
@Setter
@NoArgsConstructor
public class WigGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDate dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore // 비밀번호 노출 방지
    private User user;

    @OneToMany(mappedBy = "wigGoal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LeadMeasure> leadMeasures = new ArrayList<>();
}


