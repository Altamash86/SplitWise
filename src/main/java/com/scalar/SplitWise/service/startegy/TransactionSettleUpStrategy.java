package com.scalar.SplitWise.service.startegy;

import com.scalar.SplitWise.dto.TransactionDTO;
import com.scalar.SplitWise.model.Expense;

import java.util.List;

public interface TransactionSettleUpStrategy {
    List<TransactionDTO> settleUp(List<Expense> expenses);
}
