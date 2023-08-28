package com.example.splitwise.services;

import com.example.splitwise.models.Expense;

import com.example.splitwise.repositories.ExpenseRepository;

import com.example.splitwise.strategies.SettleUpStrategy;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    private final ExpenseRepository expenseRepository;

    private final SettleUpStrategy settleUpExpensesStrategy;

    public UserService(ExpenseRepository expenseRepository, SettleUpStrategy settleUpExpensesStrategy) {
        this.expenseRepository = expenseRepository;
        this.settleUpExpensesStrategy = settleUpExpensesStrategy;
    }

    public List<Expense> settleUp(Long userId) {

        List<Expense> expenses = expenseRepository.findAllByPaidByContainingOrOwedByContaining(userId, userId);

        return settleUpExpensesStrategy.settleUp(expenses);

    }
}
