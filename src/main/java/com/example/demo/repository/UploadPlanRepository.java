package com.example.demo.repository;
import com.example.demo.model.User;
import com.example.demo.model.UploadPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UploadPlanRepository
        extends JpaRepository<UploadPlan, Long> {

    List<UploadPlan> findByUserId(
            Long userId
    );

    long countByUserIdAndStatus(
            Long userId,
            String status
    );

    List<UploadPlan>
    findTop3ByUserIdAndStatusOrderByUploadDateAsc(
            Long userId,
            String status
    );

    List<UploadPlan>
    findTop5ByUserIdAndStatusOrderByUploadDateAsc(
            Long userId,
            String status
    );

    List<UploadPlan>
    findTop5ByUserIdOrderByIdDesc(
            Long userId
    );

    List<UploadPlan>
    findByUserIdAndStatus(
            Long userId,
            String status
    );
    
    
    
    

    List<UploadPlan> findByStatus(String status);
	long countByUserId(Long userId);
    
    
}