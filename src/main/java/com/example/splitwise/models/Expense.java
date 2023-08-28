package com.example.splitwise.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Entity
@Getter
@Setter
public class Expense extends BaseModel{
    private String description;

    private int amount;

    @ManyToOne
    private User createdBy;

    @OneToMany(mappedBy = "expense")
    private List<ExpenseUser> expenseUsers;

    @ManyToOne
    private Group group;


    @Enumerated(EnumType.ORDINAL)
    private ExpenseType expenseType;
}
