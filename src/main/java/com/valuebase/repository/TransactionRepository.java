package com.valuebase.repository;

import com.valuebase.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByCustomerId(final Long customerId);

    List<Transaction> findByCustomerIdAndTransactionDtBetween(final Long customerId, LocalDate quarterStartDate, LocalDate quarterEndDate);
}
