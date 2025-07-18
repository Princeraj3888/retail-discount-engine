package com.retail.service.impl;

import com.retail.entity.Customer;
import com.retail.entity.Item;
import com.retail.service.DiscountStrategy;
import com.retail.service.FlatDiscountCalculator;
import com.retail.service.NoDiscountStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiscountService {

    private final List<DiscountStrategy> strategies;
    private final FlatDiscountCalculator flatDiscountCalculator;

    public DiscountResult applyDiscount(Customer customer, List<Item> items) {
        BigDecimal total = items.stream()
                .map(Item::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Choose the best applicable percentage discount
        DiscountStrategy bestStrategy = strategies.stream()
                .filter(s -> s.isApplicable(customer))
                .sorted(Comparator.comparing(DiscountStrategy::getPriority).reversed())
                .findFirst()
                .orElse(new NoDiscountStrategy());

        BigDecimal percentageDiscount = bestStrategy.calculateDiscount(customer, items);

        // Apply flat discount after percentage discount
        BigDecimal flatDiscount = flatDiscountCalculator.calculate(total, percentageDiscount);

        BigDecimal totalDiscount = percentageDiscount.add(flatDiscount);
        BigDecimal netAmount = total.subtract(totalDiscount);

        return new DiscountResult(
                total,
                percentageDiscount,
                flatDiscount,
                totalDiscount,
                netAmount,
                bestStrategy.getName()
        );
    }
}
