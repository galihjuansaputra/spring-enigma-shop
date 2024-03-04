package com.enigma.enigma_shop.services;

import com.enigma.enigma_shop.entity.UserAccount;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserAccount getByUserId(String id);
}
