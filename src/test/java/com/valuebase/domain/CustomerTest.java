package com.valuebase.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class CustomerTest {

    @Test
    public void customerTest() throws Exception {
        final Customer customer = new Customer();
        customer.setId(1L);
        customer.setCustomerName("Niranjan");
        customer.setCreatedDt(LocalDateTime.now());
        customer.setUpdateDt(LocalDateTime.now());
        Assertions.assertEquals(customer.getId(), 1L);
        Assertions.assertEquals(customer.getCustomerName(), "Niranjan");
    }
}
