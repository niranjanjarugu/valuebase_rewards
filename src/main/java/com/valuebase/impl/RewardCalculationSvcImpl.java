package com.valuebase.impl;

import com.valuebase.constants.Constants;
import com.valuebase.domain.Customer;
import com.valuebase.domain.Reward;
import com.valuebase.domain.Transaction;
import com.valuebase.dto.RewardDto;
import com.valuebase.repository.CustomerRepository;
import com.valuebase.repository.RewardRepository;
import com.valuebase.repository.TransactionRepository;
import com.valuebase.service.DateCalculationSvc;
import com.valuebase.service.RewardCalculationSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RewardCalculationSvcImpl implements RewardCalculationSvc {

    @Autowired private TransactionRepository transactionRepository;

    @Autowired private CustomerRepository customerRepository;

    @Autowired private RewardRepository rewardRepository;

    @Autowired private DateCalculationSvc dateCalculationSvc;


    @Override
    public void saveRewardsToDb() {  // This will use for, when transaction save to DB....
        final List<Customer> customerList = customerRepository.findAll();
        customerList.stream().forEach(customer ->  {
            final List<Transaction> transactionList = transactionRepository.findByCustomerId(customer.getId());
            transactionList.stream().forEach(transaction ->  {
                final Reward reward = new Reward();
                reward.setTransactionId(transaction.getId());
                reward.setCustomerId(customer.getId());
                reward.setRewardPoints(getRewardPointCalculation(transaction.getAmount()));
            });
        });
    }

    @Override
    public List<RewardDto> calculateRewardPoints() {
        final List<Customer> customerList = customerRepository.findAll();
        final List<RewardDto> rewardDtoList = new ArrayList<>();
        customerList.stream().forEach(customer -> {
            final RewardDto rewardDto = calculateRewardPointsByCustomerId(customer.getId());
            rewardDtoList.add(rewardDto);
        });
        return rewardDtoList;
    }

    @Override
    public RewardDto calculateRewardPointsByCustomerId(final Long customerId) {
        final RewardDto rewardDto = getRewardPointsByCustomerId(customerId);
        return rewardDto;
    }

    private RewardDto getRewardPointsByCustomerId(final Long customerId) {
        final Integer currentQuarter = LocalDate.now().get(IsoFields.QUARTER_OF_YEAR);
        final RewardDto rewardDto = new RewardDto();
        rewardDto.setCustomerId(customerId);
        final LocalDate quarterStartDate = dateCalculationSvc.getCurrentQuarterStartDate(currentQuarter);
        final LocalDate quarterEndDate = dateCalculationSvc.getCurrentQuarterEndDate(currentQuarter);
        final List<Transaction> totalMonthsTransactionList = transactionRepository.findByCustomerIdAndTransactionDtBetween(customerId, quarterStartDate, quarterEndDate);
        rewardDto.setTotalRewardPoints(getTransactionRewardPoints(totalMonthsTransactionList));
        final Map<Integer, LocalDate> quarterStartMonthMap = dateCalculationSvc.getQuarterMonthStartDateMap(currentQuarter);
        final Map<Integer, LocalDate> quarterEndMonthMap = dateCalculationSvc.getQuarterMonthEndDateMap(currentQuarter);
        final List<Transaction> firstMonthTransactionList = transactionRepository.findByCustomerIdAndTransactionDtBetween(customerId, quarterStartMonthMap.get(Constants.ONE), quarterEndMonthMap.get(Constants.ONE));
        rewardDto.setFirstMonthRewardPoints(getTransactionRewardPoints(firstMonthTransactionList));
        final List<Transaction> secondMonthTransactionList = transactionRepository.findByCustomerIdAndTransactionDtBetween(customerId, quarterStartMonthMap.get(Constants.TWO), quarterEndMonthMap.get(Constants.TWO));
        rewardDto.setSecondMonthRewardPoints(getTransactionRewardPoints(secondMonthTransactionList));
        final List<Transaction> thirdMonthTransactionList = transactionRepository.findByCustomerIdAndTransactionDtBetween(customerId, quarterStartMonthMap.get(Constants.THREE), quarterEndMonthMap.get(Constants.THREE));
        rewardDto.setThirdMonthRewardPoints(getTransactionRewardPoints(thirdMonthTransactionList));
        return rewardDto;
    }

    private BigDecimal getTransactionRewardPoints(final List<Transaction> transactionList) {
        BigDecimal totalRewardPoints = new BigDecimal(0);
        for (final Transaction transaction : transactionList) {
            final BigDecimal finalRewardPoints = getRewardPointCalculation(transaction.getAmount());
            totalRewardPoints = totalRewardPoints.add(finalRewardPoints);
        }
        return totalRewardPoints;
    }

    private BigDecimal getRewardPointCalculation(final BigDecimal transactionAmount) {
        BigDecimal rewardPointSum;
        if (transactionAmount.compareTo(Constants.FIFTY) == -1) {
            rewardPointSum = new BigDecimal(0.00);
        } else if(transactionAmount.compareTo(Constants.HUNDRED) == Constants.ONE){
            rewardPointSum = transactionAmount.subtract(Constants.HUNDRED);
            rewardPointSum = rewardPointSum.multiply(new BigDecimal(2.00));
            rewardPointSum = rewardPointSum.add(Constants.FIFTY);
        } else {
            rewardPointSum = transactionAmount.subtract(Constants.FIFTY);
        }
        BigDecimal finalRewardPoints = rewardPointSum.setScale(2, RoundingMode.CEILING);
        return finalRewardPoints;
    }
}
