package com.valuebase.controller;

import com.valuebase.domain.Transaction;
import com.valuebase.service.TransactionSvc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TransactionControllerTest {

    @InjectMocks private TransactionController transactionController;

    @Mock private TransactionSvc transactionSvc;

    @Test
    public void getAllTransactions() throws Exception {
        final List<Transaction> transactionList = new ArrayList<>();
        final Transaction transaction = new Transaction();
        transaction.setAmount(new BigDecimal(90));
        transaction.setTransactionDt(LocalDate.now());
        transaction.setCreatedDt(LocalDateTime.now());
        transaction.setUpdateDt(LocalDateTime.now());
        transaction.setId(1L);
        transaction.setCustomerId(1L);
        transactionList.add(transaction);
        Mockito.when(transactionSvc.getAllTransactions()).thenReturn(transactionList);
        final ResponseEntity<List<Transaction>> response = transactionController.getAllTransactions();
        Assertions.assertEquals(response.getStatusCode().value(), 200);
        Assertions.assertEquals(response.getBody().size(), 1);
    }
}
