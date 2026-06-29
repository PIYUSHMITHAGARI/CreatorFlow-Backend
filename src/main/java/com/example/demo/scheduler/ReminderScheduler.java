package com.example.demo.scheduler;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.model.UploadPlan;
import com.example.demo.model.User;
import com.example.demo.repository.UploadPlanRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EmailService;

@Component
public class ReminderScheduler {

    @Autowired
    private UploadPlanRepository uploadPlanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(fixedRate = 60000)
    public void checkMissedUploads() {

        System.out.println("===== Scheduler Running =====");

        List<UploadPlan> uploads =
                uploadPlanRepository.findByStatus("Upcoming");

        System.out.println("Upcoming Uploads Found: " + uploads.size());

        for (UploadPlan upload : uploads) {

            try {

                LocalDate uploadDate =
                        LocalDate.parse(upload.getUploadDate());

                LocalTime uploadTime =
                        LocalTime.parse(upload.getUploadTime());

                System.out.println("--------------------------------");
                System.out.println("Title: " + upload.getTitle());
                System.out.println("Current Date: " + LocalDate.now());
                System.out.println("Current Time: " + LocalTime.now());
                System.out.println("Upload Date: " + uploadDate);
                System.out.println("Upload Time: " + uploadTime);

                if (
                	    uploadDate.isBefore(LocalDate.now())
                	    ||
                	    (
                	        uploadDate.equals(LocalDate.now())
                	        &&
                	        LocalTime.now().isAfter(uploadTime)
                	    )
                	) {

                    System.out.println("EMAIL CONDITION MATCHED");

                    System.out.println(
                            "User ID = "
                                    + upload.getUserId()
                    );

                    User user =
                            userRepository.findById(
                                    upload.getUserId()
                            ).orElse(null);

                    if (user == null) {

                        System.out.println(
                                "USER NOT FOUND"
                        );

                        continue;
                    }

                    System.out.println(
                            "User Name: "
                                    + user.getName()
                    );

                    System.out.println(
                            "Sending Email To: "
                                    + user.getEmail()
                    );

                    try {

                        emailService.sendReminderEmail(
                                user.getEmail(),
                                user.getName(),
                                upload.getTitle()
                        );

                        System.out.println(
                                "EMAIL SENT SUCCESSFULLY"
                        );

                        upload.setStatus("Missed");

                        uploadPlanRepository.save(upload);

                        System.out.println(
                                "UPLOAD STATUS UPDATED TO MISSED"
                        );

                    } catch (Exception e) {

                        System.out.println(
                                "EMAIL FAILED"
                        );

                        e.printStackTrace();
                    }
                }

            } catch (Exception e) {

                System.out.println(
                        "ERROR PROCESSING UPLOAD: "
                                + upload.getTitle()
                );

                e.printStackTrace();
            }
        }
    }
}