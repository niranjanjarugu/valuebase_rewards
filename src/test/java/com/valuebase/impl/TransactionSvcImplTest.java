package com.valuebase.impl;


import com.valuebase.domain.Transaction;
import com.valuebase.repository.TransactionRepository;
import com.valuebase.service.TransactionSvc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TransactionSvcImplTest {

    @InjectMocks private TransactionSvcImpl transactionSvc;
    @Mock private TransactionRepository transactionRepository;

    @Test
    public void getAllTransactionsTest() throws Exception {
        final List<Transaction> transactionList = new ArrayList<>();
        final Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setCustomerId(1L);
        transaction.setAmount(new BigDecimal(120));
        transaction.setTransactionDt(LocalDate.now());
        transaction.setCreatedDt(LocalDateTime.now());
        transaction.setUpdateDt(LocalDateTime.now());
        transactionList.add(transaction);
        Mockito.when(transactionRepository.findAll()).thenReturn(transactionList);
        final List<Transaction> actualTransactions = transactionSvc.getAllTransactions();

        Assertions.assertNotNull(transactionList);
        Assertions.assertEquals(actualTransactions.size(), 1);
        Assertions.assertEquals(actualTransactions.get(0).getCustomerId(), 1L);
    }

}
