package com.enigma.enigma_shop.dto.request;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchCustomerRequest {
    private String name;
    private String mobilePhoneNo;
    private Date birthDate;
    private Boolean status;
}
