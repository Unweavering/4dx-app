package com._dx.service;

import com._dx.model.LeadMeasure;
import com._dx.model.WigGoal;
import com._dx.repository.LeadMeasureRepository;
import com._dx.repository.WigGoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeadMeasureService {

    private final LeadMeasureRepository leadMeasureRepository;
    private final WigGoalRepository wigGoalRepository;

    public LeadMeasure create(Long wigGoalId, String name, String unit) {
        WigGoal wigGoal = wigGoalRepository.findById(wigGoalId)
                .orElseThrow(() -> new IllegalArgumentException("WIG 목표 없음"));
        LeadMeasure leadMeasure = new LeadMeasure();
        leadMeasure.setName(name);
        leadMeasure.setUnit(unit);
        leadMeasure.setWigGoal(wigGoal);
        return leadMeasureRepository.save(leadMeasure);
    }

    public List<LeadMeasure> getAllByWigGoal(Long wigGoalId) {
        return leadMeasureRepository.findAllByWigGoal_Id(wigGoalId);
    }

    public LeadMeasure update(Long id, String name, String unit) {
        LeadMeasure lm = leadMeasureRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("리드메저 없음"));
        lm.setName(name);
        lm.setUnit(unit);
        return leadMeasureRepository.save(lm);
    }

    public void delete(Long id) {
        leadMeasureRepository.deleteById(id);
    }
}
