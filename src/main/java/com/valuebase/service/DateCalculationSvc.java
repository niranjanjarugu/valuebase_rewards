package com.valuebase.service;

import java.time.LocalDate;
import java.util.Map;

public interface DateCalculationSvc {

    LocalDate getCurrentQuarterStartDate(final Integer currentQuarter);
    LocalDate getCurrentQuarterEndDate(final Integer currentQuarter);

    Map<Integer,LocalDate> getQuarterMonthStartDateMap(final Integer currentQuarter);
    Map<Integer,LocalDate> getQuarterMonthEndDateMap(final Integer currentQuarter);
    Boolean isLeapYear(final Integer currentYear);

}
