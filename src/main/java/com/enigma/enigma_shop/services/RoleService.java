package com.enigma.enigma_shop.services;


import com.enigma.enigma_shop.constant.UserRole;
import com.enigma.enigma_shop.entity.Role;

public interface RoleService {

    Role getOrSave(UserRole role);
}
