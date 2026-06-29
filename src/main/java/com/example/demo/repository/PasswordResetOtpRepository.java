package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.PasswordResetOtp;

public interface PasswordResetOtpRepository
        extends JpaRepository<
        PasswordResetOtp,
        Long> {

    PasswordResetOtp findByEmail(
            String email
    );
}