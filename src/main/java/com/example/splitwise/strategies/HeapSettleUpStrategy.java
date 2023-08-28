package com.example.splitwise.strategies;

import com.example.splitwise.models.*;

import java.util.*;

public class HeapSettleUpStrategy implements SettleUpStrategy{
    @Override
    public List<Expense> settleUp(List<Expense> expensesToSettle) {
        PriorityQueue<Record> positive = new PriorityQueue<>(Collections.reverseOrder()); // MAX Heap
        PriorityQueue<Record> negative = new PriorityQueue<>();
        Map<User, Integer> extraAmount = new HashMap<>();
        for (Expense expense: expensesToSettle) {
            for (ExpenseUser expenseUser:
                 expense.getExpenseUsers()) {
                if(expenseUser.getExpenseUserType().equals(ExpenseUserType.PAID_BY)){
                    extraAmount.put(expenseUser.getUser(), extraAmount.getOrDefault(expenseUser.getUser(), 0)+ expenseUser.getAmount());}else{
                    extraAmount.put(expenseUser.getUser(), extraAmount.getOrDefault(expenseUser.getUser(), 0)- expenseUser.getAmount());

                }
            }
        }
        for (User user: extraAmount.keySet()) {
            if(extraAmount.get(user) > 0){
                positive.add(new Record(user, extraAmount.get(user)));
            }else{
                negative.add(new Record(user, extraAmount.get(user)));
            }
        }
        List<Expense> transactions = new ArrayList<>();
        while (!positive.isEmpty() && !negative.isEmpty()){
            Record postiveRecord = positive.poll();
            Record negativeRecord = negative.poll();

            assert negativeRecord != null;
            if(postiveRecord.pendingAmount > Math.abs(negativeRecord.pendingAmount)){
                positive.add(new Record(postiveRecord.user, postiveRecord.pendingAmount - negativeRecord.pendingAmount));
                Expense addExpense = getExpense( negativeRecord, postiveRecord);
                transactions.add(addExpense);
            } else {
                negative.add(new Record(negativeRecord.user, Math.abs(negativeRecord.pendingAmount) - postiveRecord.pendingAmount));
                Expense addExpense = getExpense(negativeRecord, postiveRecord);
                transactions.add(addExpense);
            }
        }
        return transactions;
    }

    private static Expense getExpense(Record negativeRecord, Record postiveRecord) {
        Expense addExpense = new Expense();
        addExpense.setExpenseType(ExpenseType.TRANSACTION);
        addExpense.setCreatedBy(negativeRecord.user);
        addExpense.setAmount(Math.min(postiveRecord.pendingAmount,Math.abs(negativeRecord.pendingAmount)) );
        List<ExpenseUser> expenseUsers = new ArrayList<>();
        ExpenseUser paidUser = new ExpenseUser(negativeRecord.user, addExpense, addExpense.getAmount(), ExpenseUserType.PAID_BY);
        ExpenseUser receivedUser = new ExpenseUser(postiveRecord.user, addExpense, 0, ExpenseUserType.HAD_TO_PAY);
        expenseUsers.add(paidUser);
        expenseUsers.add(receivedUser);
        addExpense.setExpenseUsers(expenseUsers);
        return addExpense;
    }

    static class Record {
        User user;
        int pendingAmount;

        Record(User user, int pendingAmount) {
            this.user = user;
            this.pendingAmount = pendingAmount;
        }
    }
}
