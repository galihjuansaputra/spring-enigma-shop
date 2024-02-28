package com.enigma.enigma_shop.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchProductRequest {
    private Integer page;
    private Integer size;
    private String sortBy;
    private String direction;
    private String name;


}
