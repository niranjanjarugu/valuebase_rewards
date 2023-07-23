package com.valuebase.service;

import com.valuebase.domain.Reward;
import com.valuebase.dto.RewardDto;

import java.util.List;

public interface RewardCalculationSvc {

    void saveRewardsToDb();

    List<RewardDto> calculateRewardPoints();
    RewardDto calculateRewardPointsByCustomerId(final Long customerId);
}
