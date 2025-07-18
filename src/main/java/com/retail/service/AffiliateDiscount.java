package com.retail.service;

import com.retail.entity.Category;
import com.retail.entity.Customer;
import com.retail.entity.Item;
import com.retail.entity.Role;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class AffiliateDiscount implements DiscountStrategy {

    @Override
    public boolean isApplicable(Customer customer) {
        return customer.getRole() == Role.AFFILIATE;
    }

    @Override
    public BigDecimal calculateDiscount(Customer customer, List<Item> items) {
        BigDecimal eligibleAmount = items.stream()
                .filter(i -> i.getCategory() != Category.GROCERY)
                .map(Item::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return eligibleAmount.multiply(BigDecimal.valueOf(0.10));
    }

    @Override
    public int getPriority() {
        return 2; // Between Employee and LoyalCustomer
    }

    @Override
    public String getName() {
        return "Affiliate Discount";
    }
}
