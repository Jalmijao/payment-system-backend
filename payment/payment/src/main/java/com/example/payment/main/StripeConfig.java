package com.example.payment.main;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import com.stripe.Stripe;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfig {

    @Value("${stripe.secret-key}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
        System.out.println("✅ Stripe initialized with secret key.");
    }
}
