package com.valuebase.controller;

import com.valuebase.domain.Customer;
import com.valuebase.domain.Transaction;
import com.valuebase.dto.RewardDto;
import com.valuebase.repository.CustomerRepository;
import com.valuebase.repository.TransactionRepository;
import com.valuebase.service.RewardCalculationSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/rewards")
public class RewardController {

    private final RewardCalculationSvc rewardCalculationSvc;

    @Autowired private CustomerRepository customerRepository;
    @Autowired private TransactionRepository transactionRepository;

    public RewardController(final RewardCalculationSvc rewardCalculationSvc) {
        this.rewardCalculationSvc = rewardCalculationSvc;
    }

    @GetMapping
    public ResponseEntity<List<RewardDto>> getAllRewards() {
//        saveCustomer();
//        saveTransactions();   // These two for Testing purpose otherwise can update more scripts and will test
         return new ResponseEntity<>(rewardCalculationSvc.calculateRewardPoints(), HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<RewardDto> getRewardByCustomerId(@PathVariable("customerId") final Long customerId) {
            return new ResponseEntity<>(rewardCalculationSvc.calculateRewardPointsByCustomerId(customerId), HttpStatus.OK);
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
