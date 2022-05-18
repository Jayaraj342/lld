package com.example.lld.splitwise.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class PaymentGraph {
    Map<User, BalanceMap> graph;
}
