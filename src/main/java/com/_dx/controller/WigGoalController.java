package com._dx.controller;

import com._dx.dto.wig.WigGoalRequest;
import com._dx.dto.wig.WigGoalResponse;
import com._dx.dto.wig.WigGoalUpdateRequest;
import com._dx.dto.common.MessageResponse;
import com._dx.service.WigGoalService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
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

    private String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PostMapping
    public ResponseEntity<WigGoalResponse> createWigGoal(@RequestBody @Valid WigGoalRequest request) {
        String username = getCurrentUsername();
        WigGoalResponse created = wigGoalService.createWigGoal(username, request);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<WigGoalResponse>> getWigGoals() {
        String username = getCurrentUsername();
        List<WigGoalResponse> goals = wigGoalService.getWigGoals(username);
        return ResponseEntity.ok(goals);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WigGoalResponse> updateWigGoal(
            @PathVariable Long id,
            @RequestBody @Valid WigGoalUpdateRequest request
    ) {
        String username = getCurrentUsername();
        WigGoalResponse updated = wigGoalService.updateWigGoal(username, id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteWigGoal(@PathVariable Long id) {
        String username = getCurrentUsername();
        wigGoalService.deleteWigGoal(username, id);
        return ResponseEntity.ok(new MessageResponse("WIG 목표가 삭제되었습니다."));
    }
}
