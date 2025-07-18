package com.retail.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountResult {
    private BigDecimal totalAmount;
    private BigDecimal percentageDiscount;
    private BigDecimal flatDiscount;
    private BigDecimal totalDiscount;
    private BigDecimal netPayable;
    private String discountApplied;
}
