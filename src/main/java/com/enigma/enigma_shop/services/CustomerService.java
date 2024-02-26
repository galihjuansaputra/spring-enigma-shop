package com.enigma.enigma_shop.services;

import com.enigma.enigma_shop.entity.Customer;

import java.util.Date;
import java.util.List;

public interface CustomerService {

    Customer create(Customer customer);
    Customer getById(String id);
    List<Customer> getAll(String name, String phone, Date birth, Boolean status);
    Customer update(Customer customer);
    void delete(String id);
}
