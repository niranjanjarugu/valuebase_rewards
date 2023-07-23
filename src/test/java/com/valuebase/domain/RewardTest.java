package com.valuebase.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest
public class RewardTest {

    @Test
    public void rewardTest() throws Exception {
        final Reward reward = new Reward();
        reward.setId(1L);
        reward.setCustomerId(1L);
        reward.setRewardPoints(new BigDecimal(120));
        reward.setTransactionId(1L);
        reward.setCreatedDt(LocalDateTime.now());
        reward.setUpdateDt(LocalDateTime.now());
        Assertions.assertNotNull(reward);
        Assertions.assertEquals(reward.getId(), 1L);
    }
}
