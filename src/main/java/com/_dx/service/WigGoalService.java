package com._dx.service;

import com._dx.model.User;
import com._dx.model.WigGoal;
import com._dx.repository.WigGoalRepository;
import com._dx.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WigGoalService {
    private final WigGoalRepository wigGoalRepository;
    private final UserRepository userRepository;

    public WigGoal createWigGoal(String username, WigGoal wigGoal) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        wigGoal.setUser(user);
        return wigGoalRepository.save(wigGoal);
    }

    public List<WigGoal> getWigGoals(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return wigGoalRepository.findByUser(user);
    }

    public WigGoal updateWigGoal(String username, Long goalId, WigGoal updatedGoal) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        WigGoal wigGoal = wigGoalRepository.findById(goalId)
                .orElseThrow(() -> new RuntimeException("WIG goal not found"));
        if (!wigGoal.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }
        wigGoal.setTitle(updatedGoal.getTitle());
        wigGoal.setDescription(updatedGoal.getDescription());
        wigGoal.setDueDate(updatedGoal.getDueDate());
        return wigGoalRepository.save(wigGoal);
    }

    public void deleteWigGoal(String username, Long goalId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        WigGoal wigGoal = wigGoalRepository.findById(goalId)
                .orElseThrow(() -> new RuntimeException("WIG goal not found"));
        if (!wigGoal.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }
        wigGoalRepository.delete(wigGoal);
    }
}

