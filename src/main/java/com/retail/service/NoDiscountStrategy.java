package com.retail.service;

import com.retail.entity.Customer;
import com.retail.entity.Item;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class NoDiscountStrategy implements DiscountStrategy {

    @Override
    public boolean isApplicable(Customer customer) {
        return true; // always applicable, lowest priority
    }

    @Override
    public BigDecimal calculateDiscount(Customer customer, List<Item> items) {
        return BigDecimal.ZERO;
    }

    @Override
    public int getPriority() {
        return 0; // lowest
    }

    @Override
    public String getName() {
        return "No Discount";
    }
}
