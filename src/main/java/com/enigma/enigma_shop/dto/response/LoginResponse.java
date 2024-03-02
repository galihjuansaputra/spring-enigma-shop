package com.enigma.enigma_shop.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LoginResponse {
    private String username;
    private String token;
    private List<String> roles;
}
