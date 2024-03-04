package com.enigma.enigma_shop.services;

import com.enigma.enigma_shop.dto.response.JwtClaims;
import com.enigma.enigma_shop.entity.UserAccount;

public interface JwtService {

    String generateToken(UserAccount account);

    boolean verifyJwtToken(String token);

    JwtClaims geClaimsByToken(String token);

    //ambil data dari jwt menjadi dto
}
