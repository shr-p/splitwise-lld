package com.example.splitwise.strategies;

import com.example.splitwise.models.Expense;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SettleUpStrategy {
    List<Expense> settleUp(List<Expense> expensesToSettle);
}
