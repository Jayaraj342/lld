package com.mayank.fooddelivery.datastore;

import com.mayank.fooddelivery.model.FoodMenu;
import com.mayank.fooddelivery.model.MenuItem;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
public class FoodMenuData {
    private final Map<String, FoodMenu> foodMenuById = new HashMap<>();
    private final Map<String, String> foodMenuIdByRestaurantId = new HashMap<>();
    private final Map<String, MenuItem> menuItemById = new HashMap<>();
}
