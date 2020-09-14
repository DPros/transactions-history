package com.example.consumingrest.models;

import com.example.consumingrest.IncomingTransaction;
import com.example.consumingrest.TransactionType;

import java.util.Date;
import java.util.UUID;

public class ProcessedTransaction {
    private final TransactionType type;
    private final double amount;
    private final String id;
    private final Date effectiveDate;

    public ProcessedTransaction(IncomingTransaction transaction) {
        type = transaction.getType();
        amount = transaction.getAmount();
        this.effectiveDate = new Date();
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}
