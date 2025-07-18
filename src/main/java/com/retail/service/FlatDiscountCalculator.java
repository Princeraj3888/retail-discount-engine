package com.retail.service;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class FlatDiscountCalculator {

    public BigDecimal calculate(BigDecimal totalAmount, BigDecimal percentageDiscount) {
        BigDecimal amountAfterPercentageDiscount = totalAmount.subtract(percentageDiscount);
        BigDecimal hundreds = amountAfterPercentageDiscount.divide(BigDecimal.valueOf(100), RoundingMode.FLOOR);
        return hundreds.multiply(BigDecimal.valueOf(5));
    }
}
