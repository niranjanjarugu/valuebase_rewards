package com.valuebase.service;

import com.valuebase.domain.Transaction;

import java.util.List;

public interface TransactionSvc {

    List<Transaction> getAllTransactions();
}
