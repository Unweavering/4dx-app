package com._dx.controller;

import com._dx.model.LeadMeasure;
import com._dx.service.LeadMeasureService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LeadMeasureController {

    private final LeadMeasureService leadMeasureService;

    @PostMapping("/lead-measures")
    public ResponseEntity<LeadMeasure> create(@RequestBody Map<String, String> body) {
        Long wigGoalId = Long.valueOf(body.get("wigGoalId"));
        String name = body.get("name");
        String unit = body.get("unit");
        return ResponseEntity.ok(leadMeasureService.create(wigGoalId, name, unit));
    }

    @GetMapping("/wig/{wigGoalId}/lead-measures")
    public ResponseEntity<List<LeadMeasure>> getAll(@PathVariable Long wigGoalId) {
        return ResponseEntity.ok(leadMeasureService.getAllByWigGoal(wigGoalId));
    }

    @PutMapping("/lead-measures/{id}")
    public ResponseEntity<LeadMeasure> update(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(leadMeasureService.update(id, body.get("name"), body.get("unit")));
    }

    @DeleteMapping("/lead-measures/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        leadMeasureService.delete(id);
        return ResponseEntity.ok().build();
    }
}
