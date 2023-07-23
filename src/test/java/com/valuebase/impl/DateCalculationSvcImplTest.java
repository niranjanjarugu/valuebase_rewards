package com.valuebase.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class DateCalculationSvcImplTest {

    @InjectMocks private DateCalculationSvcImpl dateCalculationSvc;

    @Test
    public void getCurrentQuarterStartDateTest() throws Exception {
        final int currentYear = LocalDate.now().getYear();
        Assertions.assertEquals(dateCalculationSvc.getCurrentQuarterStartDate(1).toString(), currentYear + "-01-01");
        Assertions.assertEquals(dateCalculationSvc.getCurrentQuarterStartDate(2).toString(), currentYear + "-04-01");
        Assertions.assertEquals(dateCalculationSvc.getCurrentQuarterStartDate(3).toString(), currentYear + "-07-01");
        Assertions.assertEquals(dateCalculationSvc.getCurrentQuarterStartDate(4).toString(), currentYear + "-09-01");
    }

    @Test
    public void getCurrentQuarterEndDateTest() throws Exception {
        final int currentYear = LocalDate.now().getYear();
        Assertions.assertEquals(dateCalculationSvc.getCurrentQuarterEndDate(1).toString(), currentYear + "-03-31");
        Assertions.assertEquals(dateCalculationSvc.getCurrentQuarterEndDate(2).toString(), currentYear + "-06-30");
        Assertions.assertEquals(dateCalculationSvc.getCurrentQuarterEndDate(3).toString(), currentYear + "-09-30");
        Assertions.assertEquals(dateCalculationSvc.getCurrentQuarterEndDate(4).toString(), currentYear + "-12-31");
    }

    @Test
    public void getQuarterMonthStartDateMapTest() throws Exception {
        final int currentYear = LocalDate.now().getYear();
        Assertions.assertEquals(dateCalculationSvc.getQuarterMonthStartDateMap(1).get(1).toString(), currentYear + "-01-01");
        Assertions.assertEquals(dateCalculationSvc.getQuarterMonthStartDateMap(2).get(1).toString(), currentYear + "-04-01");
        Assertions.assertEquals(dateCalculationSvc.getQuarterMonthStartDateMap(3).get(1).toString(), currentYear + "-07-01");
        Assertions.assertEquals(dateCalculationSvc.getQuarterMonthStartDateMap(4).get(1).toString(), currentYear + "-10-01");
    }

    @Test
    public void getQuarterMonthEndDateMapTest() throws Exception {
        final int currentYear = LocalDate.now().getYear();
        Assertions.assertEquals(dateCalculationSvc.getQuarterMonthEndDateMap(1).get(1).toString(), currentYear + "-01-31");
        Assertions.assertEquals(dateCalculationSvc.getQuarterMonthEndDateMap(2).get(1).toString(), currentYear + "-04-30");
        Assertions.assertEquals(dateCalculationSvc.getQuarterMonthEndDateMap(3).get(1).toString(), currentYear + "-07-31");
        Assertions.assertEquals(dateCalculationSvc.getQuarterMonthEndDateMap(4).get(1).toString(), currentYear + "-10-31");
    }
}
