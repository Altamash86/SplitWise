package com.scalar.SplitWise.repository;

import com.scalar.SplitWise.model.User;
import com.scalar.SplitWise.model.UserExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserExpenseRepository extends JpaRepository<UserExpense, Integer> {
}
