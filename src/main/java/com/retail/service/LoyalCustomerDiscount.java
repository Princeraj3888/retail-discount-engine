package com.retail.service;

import com.retail.entity.Category;
import com.retail.entity.Customer;
import com.retail.entity.Item;
import com.retail.entity.Role;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class LoyalCustomerDiscount implements DiscountStrategy {

    @Override
    public boolean isApplicable(Customer customer) {
        if (customer.getRole() == Role.CUSTOMER && customer.getLoyaltyStartDate() != null) {
            LocalDate twoYearsAgo = LocalDate.now().minusYears(2);
            return customer.getLoyaltyStartDate().isBefore(twoYearsAgo);
        }
        return false;
    }

    @Override
    public BigDecimal calculateDiscount(Customer customer, List<Item> items) {
        BigDecimal eligibleAmount = items.stream()
                .filter(i -> i.getCategory() != Category.GROCERY)
                .map(Item::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return eligibleAmount.multiply(BigDecimal.valueOf(0.05));
    }

    @Override
    public int getPriority() {
        return 1; // Lower than Employee and Affiliate
    }

    @Override
    public String getName() {
        return "Loyal Customer Discount";
    }
}
