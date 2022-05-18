package com.example.lld.splitwise.service;

import com.example.lld.splitwise.dto.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupService {
    private final ExpenseService expenseService;
    private final Map<String, Group> groups;

    public GroupService(ExpenseService expenseService) {
        this.expenseService = expenseService;
        this.groups = new HashMap<>();
    }

    public PaymentGraph groupPaymentGraph(String groupId, String userId) throws IllegalAccessException {
        BalanceMap groupBalances = getBalances(groupId, userId);
        return expenseService.getPaymentGraph(groupBalances);
    }

    private BalanceMap getBalances(String groupId, String userId) throws IllegalAccessException {
        if (!groups.containsKey(groupId) && !groups.get(groupId).getUserIds().contains(userId)) {
            throw new IllegalAccessException();
        }
        return sumExpenses(expenseService.getGroupExpenses(groupId));
    }

    private BalanceMap sumExpenses(List<Expense> groupExpenses) {
        Map<User, Amount> balances = new HashMap<>();
        for (Expense expense : groupExpenses) {
            Map<User, Amount> userBalancesForThisExpense = expense.getUserBalances().getBalances();
            for (Map.Entry<User, Amount> entry : userBalancesForThisExpense.entrySet()) {
                if (!balances.containsKey(entry.getKey())) {
                    balances.put(entry.getKey(), Amount.builder().build());
                }
                Amount amountOfThisUser = balances.get(entry.getKey());
                amountOfThisUser.setAmount(amountOfThisUser.getAmount() + entry.getValue().getAmount());
            }
        }

        return new BalanceMap(balances);
    }
}
