package com.example.consumingrest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IncomingTransaction {

    private TransactionType type;
    private double amount;

    public IncomingTransaction() {
    }

    public IncomingTransaction(TransactionType type, double amount) {
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}

