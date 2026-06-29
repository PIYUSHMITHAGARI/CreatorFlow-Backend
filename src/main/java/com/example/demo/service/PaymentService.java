package com.example.demo.service;

import com.example.demo.dto.PaymentRequest;
import com.example.demo.dto.PaymentSuccessRequest;
import com.example.demo.model.Payment;
import com.example.demo.model.User;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.UserRepository;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class PaymentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Value("${razorpay.key.id}")
    private String keyId;

    @Value("${razorpay.key.secret}")
    private String keySecret;

    // -----------------------------
    // CREATE ORDER
    // -----------------------------
    public String createOrder(PaymentRequest request) throws Exception {

        if (request.getAmount() == null || request.getAmount() <= 0) {
            throw new RuntimeException("Invalid amount received");
        }

        // Razorpay client
        RazorpayClient razorpay = new RazorpayClient(keyId, keySecret);

        // Amount in rupees (frontend sends 199)
        double amountInRupees = request.getAmount();

        // Convert to paise for Razorpay
        int amountInPaise = (int) (amountInRupees * 100);

        JSONObject options = new JSONObject();
        options.put("amount", amountInPaise);
        options.put("currency", "INR");
        options.put("receipt", "creatorflow_" + System.currentTimeMillis());

        Order order = razorpay.orders.create(options);

        // Save payment in DB
        Payment payment = new Payment();

        payment.setAmount(amountInRupees); // stored in rupees (Double)
        payment.setUserId(request.getUserId());
        payment.setStatus("CREATED");
        payment.setRazorpayOrderId(order.get("id").toString());

        paymentRepository.save(payment);

        return order.toString();
    }

    // -----------------------------
    // PAYMENT SUCCESS HANDLER
    // -----------------------------
    public void paymentSuccess(PaymentSuccessRequest request) {

        Payment payment = paymentRepository
                .findByRazorpayOrderId(request.getRazorpayOrderId())
                .orElseThrow(() -> new RuntimeException("Payment Not Found"));

        payment.setStatus("PAID");
        payment.setRazorpayPaymentId(request.getRazorpayPaymentId());
        paymentRepository.save(payment);

        User user = userRepository
                .findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        user.setPremium(true);
        userRepository.save(user);

        System.out.println("USER SUCCESSFULLY UPGRADED TO PREMIUM");
    }
}