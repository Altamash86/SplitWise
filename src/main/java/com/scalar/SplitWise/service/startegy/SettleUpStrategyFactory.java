package com.scalar.SplitWise.service.startegy;

public class SettleUpStrategyFactory {
    public static TransactionSettleUpStrategy getSettleUpStrategy(){
        return new HeapTransactionSettleStrategy();
    }
}