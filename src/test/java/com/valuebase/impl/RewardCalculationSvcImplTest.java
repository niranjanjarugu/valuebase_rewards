package com.valuebase.impl;

import com.valuebase.domain.Customer;
import com.valuebase.domain.Transaction;
import com.valuebase.dto.RewardDto;
import com.valuebase.repository.CustomerRepository;
import com.valuebase.repository.RewardRepository;
import com.valuebase.repository.TransactionRepository;
import com.valuebase.service.DateCalculationSvc;
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
public class RewardCalculationSvcImplTest {

    @InjectMocks private RewardCalculationSvcImpl rewardCalculationSvc;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock private CustomerRepository customerRepository;

    @Mock private RewardRepository rewardRepository;

    @Mock private DateCalculationSvc dateCalculationSvc;

    @Test public void calculateRewardPointsTest() throws Exception {
        Mockito.when(customerRepository.findAll()).thenReturn(prepareCustomers());
        Mockito.when(transactionRepository.findByCustomerIdAndTransactionDtBetween(Mockito.anyLong(), Mockito.any(), Mockito.any())).thenReturn(prepareTransactions());
        final List<RewardDto> rewardDtoList = rewardCalculationSvc.calculateRewardPoints();
        Assertions.assertNotNull(rewardDtoList);
    }

    @Test
    public void calculateRewardPointsByCustomerIdTest() throws Exception {
        Mockito.when(customerRepository.findAll()).thenReturn(prepareCustomers());
        Mockito.when(transactionRepository.findByCustomerIdAndTransactionDtBetween(Mockito.anyLong(), Mockito.any(), Mockito.any())).thenReturn(prepareTransactions());
        final RewardDto rewardDto = rewardCalculationSvc.calculateRewardPointsByCustomerId(1L);
        Assertions.assertNotNull(rewardDto);
    }
    @Test
    public void calculateRewardPointsByCustomerIdTestWithBelowFifty() throws Exception {
        final List<Transaction> transactions = new ArrayList<>();
        final Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setCustomerId(1L);
        transaction.setAmount(new BigDecimal(40));
        transaction.setTransactionDt(LocalDate.now());
        transaction.setCreatedDt(LocalDateTime.now());
        transaction.setUpdateDt(LocalDateTime.now());
        transactions.add(transaction);
        Mockito.when(customerRepository.findAll()).thenReturn(prepareCustomers());
        Mockito.when(transactionRepository.findByCustomerIdAndTransactionDtBetween(Mockito.anyLong(), Mockito.any(), Mockito.any())).thenReturn(transactions);
        final RewardDto rewardDto = rewardCalculationSvc.calculateRewardPointsByCustomerId(1L);
        Assertions.assertNotNull(rewardDto);
    }
    @Test
    public void calculateRewardPointsByCustomerIdTestWithBelowHundred() throws Exception {
        final List<Transaction> transactions = new ArrayList<>();
        final Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setCustomerId(1L);
        transaction.setAmount(new BigDecimal(90));
        transaction.setTransactionDt(LocalDate.now());
        transaction.setCreatedDt(LocalDateTime.now());
        transaction.setUpdateDt(LocalDateTime.now());
        transactions.add(transaction);
        Mockito.when(customerRepository.findAll()).thenReturn(prepareCustomers());
        Mockito.when(transactionRepository.findByCustomerIdAndTransactionDtBetween(Mockito.anyLong(), Mockito.any(), Mockito.any())).thenReturn(transactions);
        final RewardDto rewardDto = rewardCalculationSvc.calculateRewardPointsByCustomerId(1L);
        Assertions.assertNotNull(rewardDto);
    }

    private List<Customer> prepareCustomers() {
        final List<Customer> customerList = new ArrayList<>();
        final Customer customer = new Customer();
        customer.setId(1L);
        customer.setCustomerName("Niranjan");
        customer.setCreatedDt(LocalDateTime.now());
        customer.setUpdateDt(LocalDateTime.now());
        customerList.add(customer);
        return customerList;
    }

    private List<Transaction> prepareTransactions() {
        final List<Transaction> transactions = new ArrayList<>();
        final Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setCustomerId(1L);
        transaction.setAmount(new BigDecimal(120));
        transaction.setTransactionDt(LocalDate.now());
        transaction.setCreatedDt(LocalDateTime.now());
        transaction.setUpdateDt(LocalDateTime.now());
        transactions.add(transaction);
        return transactions;
    }
}
