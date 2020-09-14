package com.example.consumingrest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "insufficient balance")
public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException() {
        super("insufficient balance");
    }
}
