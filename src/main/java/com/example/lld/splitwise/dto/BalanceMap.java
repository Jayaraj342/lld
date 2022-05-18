package com.example.lld.splitwise.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class BalanceMap {
    private Map<User, Amount> balances;

    public BalanceMap() {
        balances = new HashMap<>();
    }

    public BalanceMap(Map<User, Amount> balances) {
        this.balances = balances;
    }
}
