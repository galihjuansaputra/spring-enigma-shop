package com.enigma.enigma_shop.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RegisterResponse {
    private String username;
    private List<String> roles;
}
