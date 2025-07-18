package com.retail.service;

import com.retail.entity.Customer;
import com.retail.entity.Item;

import java.math.BigDecimal;
import java.util.List;

public interface DiscountStrategy {
    boolean isApplicable(Customer customer);
    BigDecimal calculateDiscount(Customer customer, List<Item> items);
    int getPriority(); // Used to choose the best discount
    String getName();
}
