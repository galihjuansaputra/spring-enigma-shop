package com.enigma.enigma_shop.services;

import com.enigma.enigma_shop.dto.response.JwtClaims;

public interface JwtService {

    String generateToken();
    boolean verifyJwtToken(String token);
    JwtClaims geClaimsByToken(String token);

    //ambil data dari jwt menjadi dto
}
