package com.mayank.fooddelivery.datastore;

import com.mayank.fooddelivery.model.Payment;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
public class PaymentData {
    private final Map<String, Payment> paymentById = new HashMap<>();
    private final Map<String, String> paymentIdByOrderId = new HashMap<>();
}
