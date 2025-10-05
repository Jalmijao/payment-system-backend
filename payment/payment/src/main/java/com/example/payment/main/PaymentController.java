package com.example.payment.main;


import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {


    @PostMapping("/create")
    public Map<String, Object> createPayment(@RequestBody Map<String, Object> body) throws Exception {
        try {
            Long amount = Long.parseLong(body.get("amount").toString()); // in cents (e.g. 500 = $5.00)
            String paymentMethod = body.get("method").toString();

            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(amount)
                    .setCurrency("usd")
                    .addPaymentMethodType(paymentMethod)
                    .setDescription("Test payment via Stripe + Spring Boot")
                    .build();

            PaymentIntent intent = PaymentIntent.create(params);

            Map<String, Object> response = new HashMap<>();
            response.put("clientSecret", intent.getClientSecret());
            response.put("paymentIntentId", intent.getId());
            return response;
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return errorResponse;
        }
    }



}
