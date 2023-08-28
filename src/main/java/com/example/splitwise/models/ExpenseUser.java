package com.example.splitwise.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Getter
@Setter
public class ExpenseUser extends BaseModel{
    @ManyToOne
    private User user;
    public ExpenseUser(User user, Expense expense, int amount, ExpenseUserType expenseUserType) {
        this.user = user;
        this.expense = expense;
        this.amount = amount;
        this.expenseUserType = expenseUserType;
    }

    @ManyToOne
    private Expense expense;

    private int amount;

    @Enumerated(EnumType.ORDINAL)
    private ExpenseUserType expenseUserType;


    public ExpenseUser() {

    }
}
