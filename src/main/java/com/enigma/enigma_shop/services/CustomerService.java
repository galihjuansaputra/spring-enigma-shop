package com.enigma.enigma_shop.services;

import com.enigma.enigma_shop.dto.request.SearchCustomerRequest;
import com.enigma.enigma_shop.entity.Customer;

import java.util.Date;
import java.util.List;

public interface CustomerService {

    Customer create(Customer customer);
    Customer getById(String id);

/*    List<Customer> getAll(String name, String phone, Date birth, Boolean status);*/
    List<Customer> getAll(SearchCustomerRequest request, int pageNo, int pageSize);
    Customer update(Customer customer);

    void updateStatusById(String id, Boolean status);
    void deleteById(String id);

}
