package com.mayank.fooddelivery.datastore;

import com.mayank.fooddelivery.model.Restaurant;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Getter
public class RestaurantData {
    private final Map<String, Restaurant> restaurantById = new HashMap<>();
    private final Map<String, List<String>> restaurantIdsByName = new HashMap<>();
    private final Map<String, List<String>> restaurantIdsByCity = new HashMap<>();
}
