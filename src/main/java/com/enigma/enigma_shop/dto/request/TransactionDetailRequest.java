package com.enigma.enigma_shop.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionDetailRequest {
    private String productId;
    private Integer qty;
}
