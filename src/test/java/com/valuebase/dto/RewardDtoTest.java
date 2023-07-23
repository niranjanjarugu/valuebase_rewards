package com.valuebase.dto;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class RewardDtoTest {

    @Test
    public void rewardDtoTest() throws Exception {
        final RewardDto rewardDto = new RewardDto();
        rewardDto.setCustomerId(1L);
        rewardDto.setFirstMonthRewardPoints(new BigDecimal(120));
        rewardDto.setSecondMonthRewardPoints(new BigDecimal(10));
        rewardDto.setThirdMonthRewardPoints(new BigDecimal(20));
        rewardDto.setTotalRewardPoints(new BigDecimal(140));
        Assertions.assertNotNull(rewardDto);
        Assertions.assertEquals(rewardDto.getTotalRewardPoints(), new BigDecimal(140));
    }
}
