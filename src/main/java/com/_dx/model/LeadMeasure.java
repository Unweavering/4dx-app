package com._dx.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lead_measures")
@Getter
@Setter
@NoArgsConstructor
public class LeadMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // 예: 딥워크 시간, 독서 시간 등
    private String unit; // 예: 시간, km, 회 등

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wig_goal_id")
    @JsonIgnore // 무한 순환 방지
    private WigGoal wigGoal;

    @OneToMany(mappedBy = "leadMeasure", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DailyRecord> records = new ArrayList<>();
}

