package com.scalar.SplitWise.service;

import com.scalar.SplitWise.model.*;
import com.scalar.SplitWise.repository.ExpenseRepository;
import com.scalar.SplitWise.repository.GroupRepository;
import com.scalar.SplitWise.repository.UserExpenseRepository;
import com.scalar.SplitWise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface InitService {
    void initialize();

    @Service
    class Default implements InitService{

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private GroupRepository groupRepository;

        @Autowired
        private ExpenseRepository expenseRepository;

        @Autowired
        private UserExpenseRepository userExpenseRepository;

        @Override
        public void initialize() {
            Group group = new Group();
            group.setDefaultCurrency(Currency.INR);
            group.setName("Goa");
            group.setDescription("Goa Trip");

            Group savedGroup = groupRepository.save(group);

            User userA = User.builder()
                    .name("A")
                    .email("a@email.com")
                    .mobile("123")
                    .groups(List.of(savedGroup))
                    .build();

            User userB = User.builder()
                    .name("B")
                    .email("b@email.com")
                    .mobile("234")
                    .groups(List.of(savedGroup))
                    .build();

            User userC = User.builder()
                    .name("C")
                    .email("c@email.com")
                    .mobile("345")
                    .groups(List.of(savedGroup))
                    .build();

            User userD = User.builder()
                    .name("D")
                    .email("d@email.com")
                    .mobile("456")
                    .groups(List.of(savedGroup))
                    .build();

            User userE = User.builder()
                    .name("E")
                    .email("e@email.com")
                    .mobile("567")
                    .groups(List.of(savedGroup))
                    .build();

            User userF = User.builder()
                    .name("F")
                    .email("f@email.com")
                    .mobile("678")
                    .groups(List.of(savedGroup))
                    .build();

            User savedUserA = userRepository.save(userA);
            User savedUserB = userRepository.save(userB);
            User savedUserC = userRepository.save(userC);
            User savedUserD = userRepository.save(userD);
            User savedUserE = userRepository.save(userE);
            User savedUserF = userRepository.save(userF);

            savedGroup.setUsers(List.of(savedUserA, savedUserB, savedUserC, savedUserD, savedUserE, savedUserF));
            savedGroup = groupRepository.save(savedGroup);

            UserExpense userExpenseA = new UserExpense();
            userExpenseA.setExpenseType(ExpenseType.HADTOPAY);
            userExpenseA.setAmount(500);
            userExpenseA.setUser(savedUserA);
            UserExpense savedUserExpenseA = userExpenseRepository.save(userExpenseA);

            UserExpense userExpenseB = new UserExpense();
            userExpenseB.setExpenseType(ExpenseType.HADTOPAY);
            userExpenseB.setAmount(2000);
            userExpenseB.setUser(savedUserB);
            UserExpense savedUserExpenseB = userExpenseRepository.save(userExpenseB);

            UserExpense userExpenseC = new UserExpense();
            userExpenseC.setExpenseType(ExpenseType.HADTOPAY);
            userExpenseC.setAmount(500);
            userExpenseC.setUser(savedUserC);
            UserExpense savedUserExpenseC = userExpenseRepository.save(userExpenseC);

            UserExpense userExpenseD = new UserExpense();
            userExpenseD.setExpenseType(ExpenseType.PAID);
            userExpenseD.setAmount(1500);
            userExpenseD.setUser(savedUserD);
            UserExpense savedUserExpenseD = userExpenseRepository.save(userExpenseD);

            UserExpense userExpenseE = new UserExpense();
            userExpenseE.setExpenseType(ExpenseType.PAID);
            userExpenseE.setAmount(500);
            userExpenseE.setUser(savedUserE);
            UserExpense savedUserExpenseE = userExpenseRepository.save(userExpenseE);

            UserExpense userExpenseF = new UserExpense();
            userExpenseF.setExpenseType(ExpenseType.PAID);
            userExpenseF.setAmount(1000);
            userExpenseF.setUser(savedUserF);
            UserExpense savedUserExpenseF = userExpenseRepository.save(userExpenseF);

            Expense expense = new Expense();
            expense.setDescription("Total trip expense");
            expense.setAmount(3000);
            expense.setUserExpenses(List.of(savedUserExpenseA, savedUserExpenseB, savedUserExpenseC, savedUserExpenseD, savedUserExpenseE, savedUserExpenseF));
            expense.setDefaultCurrency(Currency.INR);
            Expense savedExpense = expenseRepository.save(expense);

            savedGroup.setExpenses(List.of(savedExpense));
            groupRepository.save(savedGroup);
        }
    }
}
