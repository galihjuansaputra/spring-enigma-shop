package com.enigma.enigma_shop.services.impl;

import com.enigma.enigma_shop.entity.Customer;
import com.enigma.enigma_shop.repository.CustomerRepository;
import com.enigma.enigma_shop.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer create(Customer customer){
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public Customer getById(String id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isEmpty()) throw new RuntimeException("Customer Not Found");
        return optionalCustomer.get();
    }

    @Override
    public List<Customer> getAll(String name, String phone, Date birth, Boolean status) {
        if(name != null || phone != null || birth != null || status != null) {
            return customerRepository.findAllByNameContainingIgnoreCaseOrMobilePhoneNoOrBirthDateOrStatus(name, phone, birth, status);
        }
        return customerRepository.findAll();
    }

    @Override
    public Customer update(Customer customer) {
        getById(customer.getId());
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public void delete(String id) {
        Customer currentCustomer = getById(id);
        customerRepository.delete(currentCustomer);
    }

}
