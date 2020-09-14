package com.example.consumingrest;

import com.example.consumingrest.models.ProcessedTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.websocket.server.PathParam;
import java.util.Collection;


@RestController()
@RequestMapping("/transactions")
public class TransactionsController {
    public TransactionsController(@Autowired() TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    private final TransactionsService transactionsService;

    @GetMapping()
    public Collection<ProcessedTransaction> getTransactions() {
        return this.transactionsService.getTransactions();
    }

    @GetMapping(value = "/{id}", params = {"id"})
    public ProcessedTransaction getTransaction(@PathParam("id") String id) {
        return transactionsService.findTransaction(id);
    }

    @ResponseStatus(code = HttpStatus.CREATED, reason = "transaction stored")
    @PostMapping()
    public void addTransaction(@RequestBody() IncomingTransaction transaction) throws Exception {
        if (transaction.getAmount() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid input");
        }
        transactionsService.addTransaction(transaction);
    }
}
