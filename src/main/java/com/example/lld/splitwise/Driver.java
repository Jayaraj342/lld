package com.example.lld.splitwise;

import com.example.lld.splitwise.dto.*;
import com.example.lld.splitwise.service.ExpenseService;
import lombok.extern.java.Log;

import java.util.List;
import java.util.Map;

@Log
public class Driver {
    // toSend [(A,-80), (B,-60)] toReceive [(C,75), (D,65)]
    public static void main(String[] args) {
        BalanceMap balancesForGroupABCD = new BalanceMap
                (
                        Map.of(
                                User.builder().id("A").build(), Amount.builder().amount(-80).build(),
                                User.builder().id("B").build(), Amount.builder().amount(-60).build(),
                                User.builder().id("C").build(), Amount.builder().amount(75).build(),
                                User.builder().id("D").build(), Amount.builder().amount(65).build()
                        )
                );
        Map<String, List<Expense>> groupExpenses = Map.of(
                "ABCD", List.of(
                        Expense
                                .builder()
                                .userBalances(balancesForGroupABCD)
                                .build()
                )
        );

        ExpenseService expenseService = new ExpenseService(groupExpenses);
        PaymentGraph paymentGraph = expenseService.getPaymentGraph(balancesForGroupABCD);

        for (Map.Entry<User, BalanceMap> from : paymentGraph.getGraph().entrySet()) {
            for (Map.Entry<User, Amount> to : from.getValue().getBalances().entrySet()) {
                System.out.println(from.getKey().getId() + " -> " + to.getKey().getId() + " : " + to.getValue().getAmount());
            }
        }
    }
}
