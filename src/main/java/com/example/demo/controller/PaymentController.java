package com.example.demo.controller;

import com.example.demo.dto.PaymentRequest;
import com.example.demo.service.PaymentService;
import com.example.demo.dto.PaymentSuccessRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin("*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create-order")
    public String createOrder(
            @RequestBody PaymentRequest request
    ) throws Exception {

        return paymentService
                .createOrder(request);
    }
    
    @PostMapping("/success")
    public String paymentSuccess(
            @RequestBody
            PaymentSuccessRequest request
    ) {

        paymentService.paymentSuccess(
                request
        );

        return "Premium Activated";
    }
}