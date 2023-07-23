package com.valuebase.impl;

import com.valuebase.domain.Transaction;
import com.valuebase.repository.TransactionRepository;
import com.valuebase.service.TransactionSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionSvcImpl implements TransactionSvc {

    @Autowired private TransactionRepository transactionRepository;
    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

}
