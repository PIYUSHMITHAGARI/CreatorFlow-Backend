package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.PasswordResetOtp;
import com.example.demo.model.User;
import com.example.demo.repository.PasswordResetOtpRepository;
import com.example.demo.repository.UserRepository;

@Service
public class PasswordResetService {

    @Autowired
    private PasswordResetOtpRepository otpRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // SEND OTP

    public void sendOtp(String email) {

        User user =
                userRepository.findByEmail(email)
                        .orElse(null);

        if (user == null) {

            throw new RuntimeException(
                    "Email not registered"
            );
        }

        String otp =
                String.valueOf(
                        100000 +
                        new Random().nextInt(900000)
                );

        PasswordResetOtp existing =
                otpRepository.findByEmail(email);

        if (existing == null) {

            existing =
                    new PasswordResetOtp();
        }

        existing.setEmail(email);

        existing.setOtp(otp);

        existing.setExpiryTime(
                LocalDateTime.now()
                        .plusMinutes(5)
        );

        otpRepository.save(existing);

        emailService.sendOtpEmail(
                email,
                otp
        );
    }

    // VERIFY OTP

    public boolean verifyOtp(
            String email,
            String otp
    ) {

        PasswordResetOtp savedOtp =
                otpRepository.findByEmail(email);

        if (savedOtp == null) {
            return false;
        }

        if (
                LocalDateTime.now()
                        .isAfter(
                                savedOtp.getExpiryTime()
                        )
        ) {
            return false;
        }

        return savedOtp
                .getOtp()
                .equals(otp);
    }

    // RESET PASSWORD

    public void resetPassword(
            String email,
            String otp,
            String newPassword
    ) {

        if (!verifyOtp(email, otp)) {

            throw new RuntimeException(
                    "Invalid OTP"
            );
        }

        User user =
                userRepository.findByEmail(email)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"
                                )
                        );

        // Encrypt new password
        user.setPassword(
                passwordEncoder.encode(
                        newPassword
                )
        );

        userRepository.save(user);

        // Delete OTP after successful reset
        PasswordResetOtp savedOtp =
                otpRepository.findByEmail(email);

        if (savedOtp != null) {

            otpRepository.delete(savedOtp);
        }
    }
}