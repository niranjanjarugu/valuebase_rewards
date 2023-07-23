package com.valuebase.dto;

import java.io.Serializable;
import java.math.BigDecimal;


public class RewardDto implements Serializable {

    private Long customerId;
    private BigDecimal firstMonthRewardPoints;
    private BigDecimal secondMonthRewardPoints;
    private BigDecimal thirdMonthRewardPoints;
    private BigDecimal totalRewardPoints;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getFirstMonthRewardPoints() {
        return firstMonthRewardPoints;
    }

    public void setFirstMonthRewardPoints(BigDecimal firstMonthRewardPoints) {
        this.firstMonthRewardPoints = firstMonthRewardPoints;
    }

    public BigDecimal getSecondMonthRewardPoints() {
        return secondMonthRewardPoints;
    }

    public void setSecondMonthRewardPoints(BigDecimal secondMonthRewardPoints) {
        this.secondMonthRewardPoints = secondMonthRewardPoints;
    }

    public BigDecimal getThirdMonthRewardPoints() {
        return thirdMonthRewardPoints;
    }

    public void setThirdMonthRewardPoints(BigDecimal thirdMonthRewardPoints) {
        this.thirdMonthRewardPoints = thirdMonthRewardPoints;
    }

    public BigDecimal getTotalRewardPoints() {
        return totalRewardPoints;
    }

    public void setTotalRewardPoints(BigDecimal totalRewardPoints) {
        this.totalRewardPoints = totalRewardPoints;
    }
}
