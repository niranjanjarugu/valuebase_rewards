package com.valuebase.integrationtest.controller;

import com.valuebase.ValuebaseRewardsApplication;
import com.valuebase.domain.Customer;
import com.valuebase.domain.Transaction;
import com.valuebase.dto.RewardDto;
import com.valuebase.repository.CustomerRepository;
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
public class RewardControllerITTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired private TransactionRepository transactionRepository;
    @Autowired private CustomerRepository customerRepository;

    private String getURLWithPort(final String uri) {
        return "http://localhost:" + port + uri;
    }


    @Test
    public void getAllTransactionsTest() throws Exception {
        saveCustomer();
        saveTransactions();
        final ResponseEntity<RewardDto> response = testRestTemplate.exchange("/rewards/1", HttpMethod.GET, null,
                new ParameterizedTypeReference<RewardDto>() {});
        Assertions.assertEquals(response.getStatusCode().value(), 200);
        Assertions.assertEquals(response.getBody().getCustomerId(), 1L);
    }

    private void saveCustomer() {
        final Customer customer = new Customer();
        customer.setId(1L);
        customer.setCustomerName("Niranjan");
        customer.setCreatedDt(LocalDateTime.now());
        customer.setUpdateDt(LocalDateTime.now());
        customerRepository.save(customer);
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
