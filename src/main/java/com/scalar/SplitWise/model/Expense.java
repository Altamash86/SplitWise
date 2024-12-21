package com.scalar.SplitWise.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Expense extends BaseModel{
    private String description;
    private double amount;
    @Enumerated(EnumType.STRING)
    private Currency defaultCurrency;
    @OneToMany
    @JoinColumn(name = "expense_id")
    private List<UserExpense> userExpenses;
}
