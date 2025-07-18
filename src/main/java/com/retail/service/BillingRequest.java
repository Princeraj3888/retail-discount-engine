package com.retail.service;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillingRequest {
    private Long customerId;
    private List<Long> items;
}
