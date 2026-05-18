package com.example.ratelimiter.service;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @RateLimiter(name = "paymentService", fallbackMethod = "fallbackResponse")
    public String makePayment() {
        return "Payment Successful";
    }

    public String fallbackResponse(Exception ex) {
        return "Rate limit exceeded. Please try again later.";
    }
}
