package com.valuebase.integrationtest.controller;


import com.valuebase.ValuebaseRewardsApplication;
import com.valuebase.domain.Transaction;
import com.valuebase.domain.TransactionTest;
import com.valuebase.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ValuebaseRewardsApplication.class,
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionControllerITTest {

    @LocalServerPort
    private int port;

    @Autowired private TestRestTemplate testRestTemplate;

    @Autowired private TransactionRepository transactionRepository;

    @Test
    public void getAllTransactionsTest() throws Exception {
        saveTransactions();
        final ResponseEntity<List<Transaction>> response = testRestTemplate.exchange("/transactions", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Transaction>>() {});
        Assertions.assertEquals(response.getStatusCode().value(), 200);
        Assertions.assertEquals(response.getBody().get(0).getCustomerId(), 1L);
    }

    private String getURLWithPort(final String uri) {
        return "http://localhost:" + port + uri;
    }


    private void saveTransactions() {
        final Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setCustomerId(1L);
        transaction.setAmount(new BigDecimal(120));
        transaction.setTransactionDt(LocalDate.now());
        transaction.setCreatedDt(LocalDateTime.now());
        transaction.setUpdateDt(LocalDateTime.now());
        transactionRepository.save(transaction);
    }

}
