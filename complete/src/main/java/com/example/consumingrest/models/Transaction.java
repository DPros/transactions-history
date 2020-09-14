package com.example.consumingrest.models;

import com.example.consumingrest.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {

    private String id;
    private TransactionType type;
    private double amount;
    private Date effectiveDate;

    public Transaction() {
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getId() {
        return id;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }
}

