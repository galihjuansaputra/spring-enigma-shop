package com.enigma.enigma_shop.services.impl;

import com.enigma.enigma_shop.constant.UserRole;
import com.enigma.enigma_shop.dto.request.AuthRequest;
import com.enigma.enigma_shop.dto.response.LoginResponse;
import com.enigma.enigma_shop.dto.response.RegisterResponse;
import com.enigma.enigma_shop.entity.Customer;
import com.enigma.enigma_shop.entity.Role;
import com.enigma.enigma_shop.entity.UserAccount;
import com.enigma.enigma_shop.repository.UserAccountRepository;
import com.enigma.enigma_shop.services.AuthService;
import com.enigma.enigma_shop.services.CustomerService;
import com.enigma.enigma_shop.services.JwtService;
import com.enigma.enigma_shop.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserAccountRepository userAccountRepository;
    private final RoleService roleService;
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public RegisterResponse register(AuthRequest request) throws DataIntegrityViolationException {

            Role role = roleService.getOrSave(UserRole.ROLE_CUSTOMER);
            String hashPassword = passwordEncoder.encode(request.getPassword());

            UserAccount account = UserAccount.builder()
                    .username(request.getUsername())
                    .password(hashPassword)
                    .role(List.of(role))
                    .isEnable(true)
                    .build();

            userAccountRepository.saveAndFlush(account);

            Customer customer = Customer.builder()
                    .status(true)
                    .userAccount(account)
                    .build();
            customerService.create(customer);

            List<String> roles = account.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority).toList();

            return RegisterResponse.builder()
                    .username(account.getUsername())
                    .roles(roles)
                    .build();

    }

    @Override
    public RegisterResponse registerAdmin(AuthRequest request) {
        return null;
    }

    @Override
    public LoginResponse login(AuthRequest request) {
        String token = jwtService.generateToken();
        return LoginResponse.builder()
                .username(request.getUsername())
                .token(token)
                .build();
    }
}
