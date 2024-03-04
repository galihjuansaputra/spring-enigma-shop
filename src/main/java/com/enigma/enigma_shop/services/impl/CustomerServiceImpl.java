package com.enigma.enigma_shop.services.impl;

import com.enigma.enigma_shop.constant.UserRole;
import com.enigma.enigma_shop.dto.request.SearchCustomerRequest;
import com.enigma.enigma_shop.dto.request.UpdateCustomerRequest;
import com.enigma.enigma_shop.dto.response.CustomerResponse;
import com.enigma.enigma_shop.entity.Customer;
import com.enigma.enigma_shop.entity.UserAccount;
import com.enigma.enigma_shop.repository.CustomerRepository;
import com.enigma.enigma_shop.services.CustomerService;
import com.enigma.enigma_shop.services.UserService;
import com.enigma.enigma_shop.specification.CustomerSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final UserService userService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CustomerResponse create(Customer customer) {
        Customer newCustomer = customerRepository.saveAndFlush(customer);
        return convertCustomerToCustomerResponse(newCustomer);
    }

    @Transactional(readOnly = true)
    @Override
    public CustomerResponse getOneById(String id) {
        return convertCustomerToCustomerResponse(findByIdOrThrowNotFound(id));
    }

    @Transactional(readOnly = true)
    @Override
    public Customer getById(String id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isEmpty()) throw new RuntimeException("Customer Not Found");
        return optionalCustomer.get();
    }

    @Transactional(readOnly = true)
    @Override
    public List<CustomerResponse> getAll(SearchCustomerRequest request) {
        Specification<Customer> specification = CustomerSpecification.getSpecification(request);
        return customerRepository.findAll(specification).stream().map(customer -> convertCustomerToCustomerResponse(customer)).toList();

/*        if (name != null || phone != null || birth != null || status != null) {
            return customerRepository.findAllByNameContainingIgnoreCaseOrMobilePhoneNoOrBirthDateOrStatus(name, phone, birth, status);
        }
        return customerRepository.findAll();*/

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CustomerResponse update(UpdateCustomerRequest request) {
        Customer currentCustomer = findByIdOrThrowNotFound(request.getId());

        UserAccount userAccount = userService.getByContext();

        List<String> roles = userAccount.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        if (!userAccount.getId().equals(currentCustomer.getUserAccount().getId())) {
            throw new AccessDeniedException("cannot access this resource");
        }

        if (roles.contains(UserRole.ROLE_SUPER_ADMIN.name())){
            throw new AccessDeniedException("cannot access this resource");
        }

        if (roles.contains(UserRole.ROLE_ADMIN.name())){
            throw new AccessDeniedException("cannot access this resource");
        }

        currentCustomer.setName(request.getName());
        currentCustomer.setMobilePhoneNo(request.getMobilePhoneNo());
        currentCustomer.setAddress(request.getAddress());
        currentCustomer.setBirthDate(Date.valueOf(request.getBirthDate()));
        customerRepository.saveAndFlush(currentCustomer);
        return convertCustomerToCustomerResponse(currentCustomer);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateStatusById(String id, Boolean status) {
        findByIdOrThrowNotFound(id);
        customerRepository.updateStatus(id, status);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(String id) {
        Customer currentCustomer = findByIdOrThrowNotFound(id);
        customerRepository.delete(currentCustomer);
    }

    private Customer findByIdOrThrowNotFound(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer Not Found"));
    }

    private CustomerResponse convertCustomerToCustomerResponse(Customer newCustomer) {
        return CustomerResponse.builder()
                .id(newCustomer.getId())
                .name(newCustomer.getName())
                .mobilePhoneNo(newCustomer.getMobilePhoneNo())
                .address(newCustomer.getAddress())
                .status(newCustomer.getStatus())
                .userAccountId(newCustomer.getUserAccount().getId())
                .build();
    }

}
