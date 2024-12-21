package com.scalar.SplitWise.repository;

import com.scalar.SplitWise.model.Expense;
import com.scalar.SplitWise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
}
