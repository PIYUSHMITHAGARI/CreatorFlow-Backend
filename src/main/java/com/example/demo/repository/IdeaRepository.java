package com.example.demo.repository;

import com.example.demo.model.Idea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IdeaRepository
        extends JpaRepository<Idea, Long> {

    // Get all ideas of a user
    List<Idea> findByUserId(Long userId);

    // Latest 5 ideas for Recent Activity
    List<Idea> findTop5ByUserIdOrderByIdDesc(
            Long userId
    );

    // Count total ideas
    long countByUserId(
            Long userId
    );

    // Count ideas by status
    long countByUserIdAndStatus(
            Long userId,
            String status
    );
    
    
}