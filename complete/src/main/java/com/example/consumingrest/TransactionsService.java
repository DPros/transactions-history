package com.example.consumingrest;

import com.example.consumingrest.exceptions.InsufficientBalanceException;
import com.example.consumingrest.exceptions.TransactionNotFoundException;
import com.example.consumingrest.models.ProcessedTransaction;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service()
public class TransactionsService {

    private double balance = 0;
    private final Map<String, ProcessedTransaction> transactions = new LinkedHashMap<String, ProcessedTransaction>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void addTransaction(final IncomingTransaction transaction) throws Exception {
        lock.writeLock().lock();
        if (transaction.getType() == TransactionType.debit) {
            if (balance < transaction.getAmount()) {
                lock.writeLock().unlock();
                throw new InsufficientBalanceException();
            } else {
                balance -= transaction.getAmount();
            }
        } else if (transaction.getType() == TransactionType.credit) {
            balance += transaction.getAmount();
        } else {
            throw new RuntimeException();
        }
        ProcessedTransaction processedTransaction = new ProcessedTransaction(transaction);
        this.transactions.put(processedTransaction.getId(), processedTransaction);
        lock.writeLock().unlock();
    }

    public double getBalance() {
        double balance;
        lock.readLock().lock();
        balance = this.balance;
        lock.readLock().unlock();
        return balance;
    }

    public ProcessedTransaction findTransaction(final String id) {
//        concurrency synchronization not needed
        ProcessedTransaction transaction = transactions.get(id);
        if (transaction == null) {
            throw new TransactionNotFoundException();
        }
        return transaction;
    }

    public Collection<ProcessedTransaction> getTransactions() {
        Collection<ProcessedTransaction> transactions;
        lock.readLock().lock();
        transactions = this.transactions.values();
        lock.readLock().unlock();
        return transactions;
    }
}
