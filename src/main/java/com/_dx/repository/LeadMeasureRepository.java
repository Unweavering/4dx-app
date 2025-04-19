package com._dx.repository;

import com._dx.model.LeadMeasure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeadMeasureRepository extends JpaRepository<LeadMeasure, Long> {
    List<LeadMeasure> findAllByWigGoal_Id(Long wigGoalId);
}

