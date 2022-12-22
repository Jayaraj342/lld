package com.mayank.fooddelivery.datastore;

import com.mayank.fooddelivery.model.Order;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Getter
public class OrderData {
    private final Map<String, Order> orderById = new HashMap<>();
    private final Map<String, List<String>> orderIdsByUserId = new HashMap<>();

}
