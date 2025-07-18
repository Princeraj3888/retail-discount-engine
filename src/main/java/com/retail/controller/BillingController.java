package com.retail.controller;

import com.retail.entity.Customer;
import com.retail.entity.Item;
import com.retail.repository.CustomerRepository;
import com.retail.repository.ItemRepository;
import com.retail.service.BillingRequest;
import com.retail.service.impl.DiscountResult;
import com.retail.service.impl.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/billing")
@RequiredArgsConstructor
public class BillingController {

    private final CustomerRepository customerRepo;
    private final ItemRepository itemRepo;
    private final DiscountService discountService;

    @PostMapping("/calculate")
    public ResponseEntity<DiscountResult> calculate(@RequestBody BillingRequest request) {
        Customer customer = customerRepo.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        List<Item> items = request.getItems().stream()
                .map(id -> itemRepo.findById(id).orElseThrow(() -> new RuntimeException("Item not found: " + id)))
                .collect(Collectors.toList());

        DiscountResult result = discountService.applyDiscount(customer, items);
        return ResponseEntity.ok(result);
    }
}
