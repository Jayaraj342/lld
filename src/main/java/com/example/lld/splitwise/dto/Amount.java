package com.example.lld.splitwise.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Amount {
    private String currency;
    private double amount;
}
