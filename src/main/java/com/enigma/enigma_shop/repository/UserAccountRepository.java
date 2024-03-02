package com.enigma.enigma_shop.repository;

import com.enigma.enigma_shop.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
}
