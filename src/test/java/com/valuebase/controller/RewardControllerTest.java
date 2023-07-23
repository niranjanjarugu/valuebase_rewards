package com.valuebase.controller;

import com.valuebase.dto.RewardDto;
import com.valuebase.service.RewardCalculationSvc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class RewardControllerTest {

    @InjectMocks private RewardController rewardController;
    @Mock private RewardCalculationSvc rewardCalculationSvc;

    @Test
    public void getAllRewardsTest() throws Exception {
        final List<RewardDto> rewardDtoList = new ArrayList<>();
        final RewardDto rewardDto = new RewardDto();
        rewardDto.setCustomerId(1L);
        rewardDto.setFirstMonthRewardPoints(new BigDecimal(120));
        rewardDto.setSecondMonthRewardPoints(new BigDecimal(10));
        rewardDto.setThirdMonthRewardPoints(new BigDecimal(20));
        rewardDto.setTotalRewardPoints(new BigDecimal(140));
        rewardDtoList.add(rewardDto);
        Mockito.when(rewardCalculationSvc.calculateRewardPoints()).thenReturn(rewardDtoList);
        final ResponseEntity<List<RewardDto>> response = rewardController.getAllRewards();
        Assertions.assertEquals(response.getStatusCode().value(), 200);
        Assertions.assertEquals(response.getBody().size(), 1);
    }


    @Test
    public void getRewardByCustomerId() throws Exception {
        final RewardDto rewardDto = new RewardDto();
        rewardDto.setCustomerId(1L);
        rewardDto.setFirstMonthRewardPoints(new BigDecimal(20));
        rewardDto.setSecondMonthRewardPoints(new BigDecimal(10));
        rewardDto.setThirdMonthRewardPoints(new BigDecimal(20));
        rewardDto.setTotalRewardPoints(new BigDecimal(50));
        Mockito.when(rewardCalculationSvc.calculateRewardPointsByCustomerId(Mockito.anyLong())).thenReturn(rewardDto);
        final ResponseEntity<RewardDto> response = rewardController.getRewardByCustomerId(1L);
        Assertions.assertEquals(response.getStatusCode().value(), 200);
        Assertions.assertEquals(response.getBody().getCustomerId().intValue(), 1);
    }

}
