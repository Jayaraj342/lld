package com.example.lld.splitwise.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Expense {
    private BalanceMap userBalances;
    private String title;
    private String imageURL;
    private String description;
}
