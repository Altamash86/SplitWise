package com.scalar.SplitWise.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "splitwise_group")
@Getter
@Setter
public class Group extends BaseModel{
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private Currency defaultCurrency;
    @ManyToMany
    private List<User> users;
    private double totalAmountSpent;
    @OneToMany
    @JoinColumn(name = "splitwise_group_id")
    private List<Expense> expenses;
}
