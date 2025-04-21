package com._dx.service;

import com._dx.dto.wig.WigGoalRequest;
import com._dx.dto.wig.WigGoalUpdateRequest;
import com._dx.dto.wig.WigGoalResponse;
import com._dx.model.User;
import com._dx.model.WigGoal;
import com._dx.repository.UserRepository;
import com._dx.repository.WigGoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WigGoalService {
    private final WigGoalRepository wigGoalRepository;
    private final UserRepository userRepository;

    public WigGoalResponse createWigGoal(String username, WigGoalRequest request) {
        User user = getUser(username);

        WigGoal goal = new WigGoal();
        goal.setTitle(request.getTitle());
        goal.setDescription(request.getDescription());
        goal.setDueDate(request.getDueDate());
        goal.setUser(user);

        WigGoal saved = wigGoalRepository.save(goal);
        return toResponse(saved);
    }

    public List<WigGoalResponse> getWigGoals(String username) {
        User user = getUser(username);
        return wigGoalRepository.findByUser(user).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public WigGoalResponse updateWigGoal(String username, Long goalId, WigGoalUpdateRequest request) {
        User user = getUser(username);
        WigGoal goal = getGoal(goalId, user);

        goal.setTitle(request.getTitle());
        goal.setDescription(request.getDescription());
        goal.setDueDate(request.getDueDate());

        WigGoal updated = wigGoalRepository.save(goal);
        return toResponse(updated);
    }

    public void deleteWigGoal(String username, Long goalId) {
        User user = getUser(username);
        WigGoal goal = getGoal(goalId, user);
        wigGoalRepository.delete(goal);
    }

    // 🔒 내부 메서드
    private User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private WigGoal getGoal(Long id, User user) {
        WigGoal goal = wigGoalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Goal not found"));
        if (!goal.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }
        return goal;
    }

    private WigGoalResponse toResponse(WigGoal goal) {
        return new WigGoalResponse(
                goal.getId(),
                goal.getTitle(),
                goal.getDescription(),
                goal.getDueDate()
        );
    }
}
