package com.valuebase.impl;

import com.valuebase.constants.Constants;
import com.valuebase.service.DateCalculationSvc;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Service
public class DateCalculationSvcImpl implements DateCalculationSvc {


    @Override
    public LocalDate getCurrentQuarterStartDate(final Integer currentQuarter) {
        LocalDate quarterDate = null;
        final int currentYear = LocalDate.now().getYear();
        if(Constants.ONE.equals(currentQuarter)) {
            quarterDate = LocalDate.parse( currentYear+ "-01-01");
        } else if (Constants.TWO.equals(currentQuarter)) {
            quarterDate = LocalDate.parse(currentYear + "-04-01");
        }else if (Constants.THREE.equals(currentQuarter)) {
            quarterDate = LocalDate.parse(currentYear + "-07-01");
        }else if (Constants.FOUR.equals(currentQuarter)) {
            quarterDate = LocalDate.parse(currentYear + "-09-01");
        }
        return quarterDate;
    }

    @Override
    public LocalDate getCurrentQuarterEndDate(final Integer currentQuarter) {
        LocalDate quarterDate = null;
        final int currentYear = LocalDate.now().getYear();
        if(Constants.ONE.equals(currentQuarter)) {
            quarterDate = LocalDate.parse( currentYear+ "-03-31");
        } else if (Constants.TWO.equals(currentQuarter)) {
            quarterDate = LocalDate.parse(currentYear + "-06-30");
        }else if (Constants.THREE.equals(currentQuarter)) {
            quarterDate = LocalDate.parse(currentYear + "-09-30");
        }else if (Constants.FOUR.equals(currentQuarter)) {
            quarterDate = LocalDate.parse(currentYear + "-12-31");
        }
        return quarterDate;
    }

    @Override
    public Map<Integer,LocalDate> getQuarterMonthStartDateMap(final Integer currentQuarter) {
        final Map<Integer,LocalDate> quarterMonthMap = new HashMap<>();
        final int currentYear = LocalDate.now().getYear();
        if(currentQuarter.equals(1)) {
            quarterMonthMap.put(1, LocalDate.parse(currentYear + "-01-01"));
            quarterMonthMap.put(2, LocalDate.parse(currentYear + "-02-01"));
            quarterMonthMap.put(3, LocalDate.parse(currentYear + "-03-01"));
        }if(currentQuarter.equals(2)) {
            quarterMonthMap.put(1, LocalDate.parse(currentYear + "-04-01"));
            quarterMonthMap.put(2, LocalDate.parse(currentYear + "-05-01"));
            quarterMonthMap.put(3, LocalDate.parse(currentYear + "-06-01"));
        }if(currentQuarter.equals(3)) {
            quarterMonthMap.put(1, LocalDate.parse(currentYear + "-07-01"));
            quarterMonthMap.put(2, LocalDate.parse(currentYear + "-08-01"));
            quarterMonthMap.put(3, LocalDate.parse(currentYear + "-09-01"));
        }if(currentQuarter.equals(4)) {
            quarterMonthMap.put(1, LocalDate.parse(currentYear + "-10-01"));
            quarterMonthMap.put(2, LocalDate.parse(currentYear + "-11-01"));
            quarterMonthMap.put(3, LocalDate.parse(currentYear + "-12-01"));
        }
        return quarterMonthMap;
    }

    @Override
    public Map<Integer,LocalDate> getQuarterMonthEndDateMap(final Integer currentQuarter) {
        final Map<Integer,LocalDate> quarterMonthMap = new HashMap<>();
        final int currentYear = LocalDate.now().getYear();
        if(currentQuarter.equals(1)) {
            quarterMonthMap.put(1, LocalDate.parse(currentYear + "-01-31"));
            if(isLeapYear(currentYear)) {
                quarterMonthMap.put(2, LocalDate.parse(currentYear + "-02-29"));
            } else {
                quarterMonthMap.put(2, LocalDate.parse(currentYear + "-02-28"));
            }
            quarterMonthMap.put(3, LocalDate.parse(currentYear + "-03-31"));
        }if(currentQuarter.equals(2)) {
            quarterMonthMap.put(1, LocalDate.parse(currentYear + "-04-30"));
            quarterMonthMap.put(2, LocalDate.parse(currentYear + "-05-31"));
            quarterMonthMap.put(3, LocalDate.parse(currentYear + "-06-30"));
        }if(currentQuarter.equals(3)) {
            quarterMonthMap.put(1, LocalDate.parse(currentYear + "-07-31"));
            quarterMonthMap.put(2, LocalDate.parse(currentYear + "-08-31"));
            quarterMonthMap.put(3, LocalDate.parse(currentYear + "-09-30"));
        }if(currentQuarter.equals(4)) {
            quarterMonthMap.put(1, LocalDate.parse(currentYear + "-10-31"));
            quarterMonthMap.put(2, LocalDate.parse(currentYear + "-11-30"));
            quarterMonthMap.put(3, LocalDate.parse(currentYear + "-12-31"));
        }
        return quarterMonthMap;
    }

    public Boolean isLeapYear(final Integer currentYear) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, currentYear);
        if(calendar.getActualMaximum(Calendar.DAY_OF_YEAR) > 365) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
