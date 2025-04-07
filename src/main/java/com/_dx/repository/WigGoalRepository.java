package com._dx.repository;

import com._dx.model.WigGoal;
import com._dx.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WigGoalRepository extends JpaRepository<WigGoal, Long> {
    List<WigGoal> findByUser(User user);
}

