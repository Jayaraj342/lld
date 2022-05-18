package com.example.lld.splitwise.service;

import com.example.lld.splitwise.dto.*;

import java.util.*;

public class ExpenseService {
    private final Map<String, List<Expense>> groupExpenses;

    public ExpenseService(Map<String, List<Expense>> groupExpenses) {
        this.groupExpenses = groupExpenses;
    }

    public List<Expense> getGroupExpenses(String groupId) {
        return groupExpenses.get(groupId);
    }

    public PaymentGraph getPaymentGraph(BalanceMap groupBalances) {
        Map<User, BalanceMap> paymentGraph = new HashMap<>();

        PriorityQueue<Map.Entry<User, Amount>> toReceiveQueue = new PriorityQueue<>(Comparator.comparingDouble(a -> -a.getValue().getAmount()));
        PriorityQueue<Map.Entry<User, Amount>> toSendQueue = new PriorityQueue<>(Comparator.comparingDouble(a -> a.getValue().getAmount()));
        for (Map.Entry<User, Amount> userAmountEntry : groupBalances.getBalances().entrySet()) {
            if (userAmountEntry.getValue().getAmount() > 0) {
                toReceiveQueue.add(userAmountEntry);
            } else {
                toSendQueue.add(userAmountEntry);
            }
        }

        // toSend [(A,-80), (B,-60)] toReceive [(C,75), (D,65)]
        while (!toSendQueue.isEmpty() && !toReceiveQueue.isEmpty()) {
            Map.Entry<User, Amount> toReceive = toReceiveQueue.poll();
            Map.Entry<User, Amount> toSend = toSendQueue.poll();

            double remaining = toReceive.getValue().getAmount() + toSend.getValue().getAmount();
            if (!paymentGraph.containsKey(toSend.getKey())) {
                paymentGraph.put(toSend.getKey(), new BalanceMap());
            }

            Map<User, Amount> otherBalancesForToSendUser = paymentGraph.get(toSend.getKey()).getBalances();

            Amount amountToSend = Amount.builder().build();
            otherBalancesForToSendUser.put(toReceive.getKey(), amountToSend);
            if (remaining > 0) {
                amountToSend.setAmount(Math.abs(toSend.getValue().getAmount()));
                toReceiveQueue.add(new AbstractMap.SimpleEntry<>(toReceive.getKey(), Amount.builder().amount(remaining).build()));
            } else {
                amountToSend.setAmount(Math.abs(toReceive.getValue().getAmount()));
                toSendQueue.add(new AbstractMap.SimpleEntry<>(toSend.getKey(), Amount.builder().amount(remaining).build()));
            }
        }

        return PaymentGraph.builder().graph(paymentGraph).build();
    }
}
