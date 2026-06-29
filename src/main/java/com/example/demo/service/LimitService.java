package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.IdeaRepository;
import com.example.demo.repository.UploadPlanRepository;
import com.example.demo.repository.UserRepository;

@Service
public class LimitService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IdeaRepository ideaRepository;

    @Autowired
    private UploadPlanRepository uploadPlanRepository;

    public boolean canCreate(Long userId) {

        User user =
                userRepository.findById(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User Not Found"
                                )
                        );

        long totalIdeas =
                ideaRepository.countByUserId(userId);

        long totalUploads =
                uploadPlanRepository.countByUserId(userId);

        long total =
                totalIdeas + totalUploads;

        int limit =
                user.isPremium()
                        ? 50
                        : 25;

        return total < limit;
    }
}