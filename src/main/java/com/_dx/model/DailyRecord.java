package com._dx.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "daily_records")
@Getter
@Setter
@NoArgsConstructor
public class DailyRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private double value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_measure_id")
    @JsonIgnore
    private LeadMeasure leadMeasure;
}
