package com.example.demo.service;

import com.example.demo.model.UploadPlan;
import com.example.demo.repository.UploadPlanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UploadPlanService {

    @Autowired
    private UploadPlanRepository uploadPlanRepository;

    // Create Upload Plan

    public UploadPlan createPlan(
            UploadPlan plan
    ) {

        if (!limitService.canCreate(
                plan.getUserId()
        )) {

            throw new RuntimeException(
                    "You have reached your limit. Upgrade to Premium for 50 uploads/ideas."
            );
        }

        return uploadPlanRepository.save(
                plan
        );
    }

    // Get All Plans For User

    public List<UploadPlan> getPlansByUser(Long userId) {

        return uploadPlanRepository.findByUserId(userId);
    }

    // Get Single Plan By Id

    public UploadPlan getPlanById(Long id) {

        return uploadPlanRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Upload Plan Not Found"
                        )
                );
        
        
    }
    
    @Autowired
    private LimitService limitService;

    // Update Plan

    public UploadPlan updatePlan(
            Long id,
            UploadPlan updatedPlan
    ) {

        UploadPlan existingPlan =
                uploadPlanRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Upload Plan Not Found"
                                )
                        );

        existingPlan.setTitle(
                updatedPlan.getTitle()
        );

        existingPlan.setPlatform(
                updatedPlan.getPlatform()
        );

        existingPlan.setUploadDate(
                updatedPlan.getUploadDate()
        );

        existingPlan.setUploadTime(
                updatedPlan.getUploadTime()
        );

        existingPlan.setStatus(
                updatedPlan.getStatus()
        );

        return uploadPlanRepository.save(
                existingPlan
        );
    }

    // Delete Plan

    public void deletePlan(Long id) {

        uploadPlanRepository.deleteById(id);
    }

    // Upcoming Uploads For Dashboard

    public List<UploadPlan> getUpcomingUploads(
            Long userId
    ) {

        return uploadPlanRepository
                .findTop3ByUserIdAndStatusOrderByUploadDateAsc(
                        userId,
                        "Upcoming"
                );
    }
}