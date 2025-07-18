package com.retail;

import com.retail.entity.Category;
import com.retail.entity.Customer;
import com.retail.entity.Item;
import com.retail.entity.Role;
import com.retail.service.LoyalCustomerDiscount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LoyalCustomerDiscountTest {

    private final LoyalCustomerDiscount discount = new LoyalCustomerDiscount();

    @Test
    void testIsApplicable_whenCustomerIsLoyal_shouldReturnTrue() {
        Customer customer = new Customer();
        customer.setRole(Role.CUSTOMER);
        customer.setLoyaltyStartDate(LocalDate.now().minusYears(3));

        boolean result = discount.isApplicable(customer);
        assertTrue(result);
    }

    @Test
    void testIsApplicable_whenCustomerIsNotLoyal_shouldReturnFalse() {
        Customer customer = new Customer();
        customer.setRole(Role.CUSTOMER);
        customer.setLoyaltyStartDate(LocalDate.now().minusMonths(6));

        boolean result = discount.isApplicable(customer);
        assertFalse(result);
    }

    /*@Test
    void testCalculateDiscount_shouldReturnFivePercent() {
        Customer customer = new Customer();
        customer.setRole(Role.CUSTOMER);
        customer.setLoyaltyStartDate(LocalDate.now().minusYears(3));

        List<Item> items = List.of(
                new Item(1L, "TV", new BigDecimal("500.00"), Category.NON_GROCERY),
                new Item(2L, "Laptop", new BigDecimal("1000.00"), Category.NON_GROCERY),
                new Item(3L, "Milk", new BigDecimal("10.00"), Category.GROCERY)
        );

        BigDecimal discountAmount = discount.calculateDiscount(customer, items);
        assertEquals(new BigDecimal("75.00"), discountAmount);
    }*/
}
