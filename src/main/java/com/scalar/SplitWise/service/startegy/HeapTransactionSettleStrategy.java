package com.scalar.SplitWise.service.startegy;

import com.scalar.SplitWise.dto.TransactionDTO;
import com.scalar.SplitWise.model.Expense;
import com.scalar.SplitWise.model.ExpenseType;
import com.scalar.SplitWise.model.User;
import com.scalar.SplitWise.model.UserExpense;

import java.util.*;

public class HeapTransactionSettleStrategy implements TransactionSettleUpStrategy{

    @Override
    public List<TransactionDTO> settleUp(List<Expense> expenses) {
        Map<User,Double> outstanding = new HashMap<>();
        List<TransactionDTO> transactions = new ArrayList<>();

        for(Expense expense: expenses){
            for(UserExpense userExpense: expense.getUserExpenses()){
                User user = userExpense.getUser();
                double amount = userExpense.getExpenseType().equals(ExpenseType.PAID)?userExpense.getAmount():(-userExpense.getAmount());
                outstanding.put(user,outstanding.getOrDefault(user,0.00)+amount);
            }
        }

        PriorityQueue<Map.Entry<User,Double>> minHeap = new PriorityQueue<>((a,b)->Double.compare(a.getValue(),b.getValue()));
        PriorityQueue<Map.Entry<User,Double>> maxHeap = new PriorityQueue<>((a,b)->Double.compare(b.getValue(),a.getValue()));

        for(Map.Entry<User,Double> entry : outstanding.entrySet()){
            if(entry.getValue() < 0){
                minHeap.add(entry);
            }
            else if(entry.getValue() > 0){
                maxHeap.add(entry);
            }
            else{
                System.out.println(entry.getKey().getName() + "'s is already settled up");
            }
        }

        while(!minHeap.isEmpty()){
            var transaction1 =  minHeap.poll();
            var transaction2 = maxHeap.poll();
            assert transaction2 != null;
            double settleUpAmount = Math.min(Math.abs(transaction1.getValue()),transaction2.getValue());
            transactions.add(new TransactionDTO(transaction1.getKey().getName(),transaction2.getKey().getName(),settleUpAmount));
            transaction1.setValue(transaction1.getValue()+settleUpAmount);
            transaction2.setValue(transaction2.getValue()-settleUpAmount);
            if(transaction1.getValue()!=0){
                minHeap.add(transaction1);
            }
            if(transaction2.getValue()!=0){
                maxHeap.add(transaction2);
            }

            /* Sandeep Logic
            double newBalance = transaction1.getValue() + transaction2.getValue(); // -ve + +ve => difference
            if(newBalance == 0){ // means both were equal and settled the balances
                System.out.println("Settled for :  " + transaction1.getKey().getName() + ", and " + transaction2.getKey().getName());
            } else if(newBalance < 0) { // means person who had to pay was greater in value, so goes back t0 min Heap with new updated balance
                transaction1.setValue(newBalance);
                minHeap.add(transaction1);
            } else if(newBalance > 0){ // means person who will get paid was greater in value, so goes back t0 max Heap with new updated balance
                transaction2.setValue(newBalance);
                maxHeap.add(transaction2);
            }*/
        }
        return transactions;
    }
}