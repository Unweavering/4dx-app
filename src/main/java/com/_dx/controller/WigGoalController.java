package com._dx.controller;

import com._dx.model.WigGoal;
import com._dx.service.WigGoalService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth") // 🔐 Swagger가 이 API에 JWT를 붙이도록 지시
@RestController
@RequestMapping("/api/wig")
@RequiredArgsConstructor
public class WigGoalController {

    private final WigGoalService wigGoalService;

    // 현재 로그인된 사용자의 username을 SecurityContext에서 가져오는 메서드
    private String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PostMapping
    public ResponseEntity<WigGoal> createWigGoal(@RequestBody WigGoal wigGoal) {
        String username = getCurrentUsername();
        WigGoal created = wigGoalService.createWigGoal(username, wigGoal);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<WigGoal>> getWigGoals() {
        String username = getCurrentUsername();
        List<WigGoal> goals = wigGoalService.getWigGoals(username);
        return ResponseEntity.ok(goals);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WigGoal> updateWigGoal(@PathVariable Long id, @RequestBody WigGoal updatedGoal) {
        String username = getCurrentUsername();
        WigGoal updated = wigGoalService.updateWigGoal(username, id, updatedGoal);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWigGoal(@PathVariable Long id) {
        String username = getCurrentUsername();
        wigGoalService.deleteWigGoal(username, id);
        return ResponseEntity.ok("Deleted");
    }
}
