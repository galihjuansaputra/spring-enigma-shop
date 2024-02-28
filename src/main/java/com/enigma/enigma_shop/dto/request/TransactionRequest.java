package com.enigma.enigma_shop.dto.request;

import com.enigma.enigma_shop.entity.TransactionDetail;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TransactionRequest {
        private String customerId;
        private List<TransactionDetailRequest> transactionDetails;
}
