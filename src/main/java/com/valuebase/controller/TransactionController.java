package com.valuebase.controller;

import com.valuebase.domain.Transaction;
import com.valuebase.service.TransactionSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired private TransactionSvc transactionSvc;

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        try {
            return new ResponseEntity<>(transactionSvc.getAllTransactions(), HttpStatus.OK);
        } catch (final Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
