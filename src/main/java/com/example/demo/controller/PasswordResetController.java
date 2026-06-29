package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ForgotPasswordRequest;
import com.example.demo.dto.ResetPasswordRequest;
import com.example.demo.dto.VerifyOtpRequest;
import com.example.demo.service.PasswordResetService;

@RestController
@RequestMapping("/api/password")
@CrossOrigin("*")
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;

    @PostMapping("/forgot")
    public String sendOtp(
            @RequestBody
            ForgotPasswordRequest request
    ) {

        passwordResetService.sendOtp(
                request.getEmail()
        );

        return "OTP Sent Successfully";
    }

    @PostMapping("/verify")
    public String verifyOtp(
            @RequestBody
            VerifyOtpRequest request
    ) {

        boolean valid =
                passwordResetService.verifyOtp(
                        request.getEmail(),
                        request.getOtp()
                );

        if (valid) {

            return "OTP Verified";
        }

        return "Invalid OTP";
    }

    @PostMapping("/reset")
    public String resetPassword(
            @RequestBody
            ResetPasswordRequest request
    ) {

        passwordResetService.resetPassword(
        		 request.getEmail(),
        	        request.getOtp(),
        	        request.getPassword()
        );
        
        

        return "Password Changed Successfully";
    }
}