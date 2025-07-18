package com.retail;

import com.retail.service.FlatDiscountCalculator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
class FlatDiscountCalculatorTest {

    private final FlatDiscountCalculator calculator = new FlatDiscountCalculator();

   /* @Test
    void testFlatDiscount_correctCalculation() {
        BigDecimal total = new BigDecimal("990.00");
        BigDecimal percentage = new BigDecimal("0.00");

        BigDecimal result = calculator.calculate(total, percentage);
        assertEquals(new BigDecimal("45.00"), result);
    }*/

    @Test
    void testFlatDiscount_afterPercentageDiscount() {
        BigDecimal total = new BigDecimal("990.00");
        BigDecimal percentage = new BigDecimal("90.00"); // e.g., 10% discount

        BigDecimal result = calculator.calculate(total, percentage);
        assertEquals(new BigDecimal("45.00"), result); // (990 - 90 = 900) → $45 flat
    }
}
