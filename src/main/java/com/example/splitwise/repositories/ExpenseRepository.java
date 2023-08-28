package com.example.splitwise.repositories;

import com.example.splitwise.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findAllByPaidByContainingOrOwedByContaining(Long userId, Long userId2);

}
