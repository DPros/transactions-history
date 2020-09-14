package com.example.consumingrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping("/")
public class BalanceController {
    public BalanceController(@Autowired() TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    private final TransactionsService transactionsService;

    @GetMapping(produces = "application/json")
    public double getBalance() {
        return this.transactionsService.getBalance();
    }
}
