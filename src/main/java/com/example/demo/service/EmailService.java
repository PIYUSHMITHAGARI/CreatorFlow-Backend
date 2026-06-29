package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendReminderEmail(
            String toEmail,
            String userName,
            String title
    ) {

        SimpleMailMessage message =
                new SimpleMailMessage();

        message.setTo(toEmail);

        message.setSubject(
                "Upload Reminder"
        );

        message.setText(

                "Hello " + userName +

                "\n\nYou missed your planned upload:\n\n"

                + title +

                "\n\nConsistency matters."

        );

        mailSender.send(message);
    }
    
    public void sendOtpEmail(
            String email,
            String otp
    ) {

        SimpleMailMessage message =
                new SimpleMailMessage();

        message.setTo(email);

        message.setSubject(
                "CreatorFlow Password Reset OTP"
        );

        message.setText(
                "Your OTP is: "
                        + otp
                        + "\n\nValid for 5 minutes."
        );

        mailSender.send(message);
    }
}