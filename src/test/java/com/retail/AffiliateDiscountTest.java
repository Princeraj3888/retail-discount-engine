package com.retail;

import com.retail.entity.Category;
import com.retail.entity.Customer;
import com.retail.entity.Item;
import com.retail.entity.Role;
import com.retail.service.AffiliateDiscount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AffiliateDiscountTest {

    private final AffiliateDiscount discount = new AffiliateDiscount();

    @Test
    void testIsApplicable_whenCustomerIsAffiliate_shouldReturnTrue() {
        Customer customer = new Customer();
        customer.setRole(Role.AFFILIATE);

        boolean result = discount.isApplicable(customer);
        assertTrue(result);
    }

    @Test
    void testIsApplicable_whenCustomerIsNotAffiliate_shouldReturnFalse() {
        Customer customer = new Customer();
        customer.setRole(Role.CUSTOMER);

        boolean result = discount.isApplicable(customer);
        assertFalse(result);
    }

    /*@Test
    void testCalculateDiscount_shouldReturnTenPercent() {
        Customer customer = new Customer();
        customer.setRole(Role.AFFILIATE);

        List<Item> items = List.of(
                new Item(1L, "Monitor", new BigDecimal("300.00"), Category.NON_GROCERY),
                new Item(2L, "Bread", new BigDecimal("20.00"), Category.GROCERY)
        );

        BigDecimal discountAmount = discount.calculateDiscount(customer, items);
        assertEquals(new BigDecimal("30.00"), discountAmount);
    }*/
}
