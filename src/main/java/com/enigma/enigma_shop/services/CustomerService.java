package com.enigma.enigma_shop.services;

import com.enigma.enigma_shop.dto.request.SearchCustomerRequest;
import com.enigma.enigma_shop.dto.request.UpdateCustomerRequest;
import com.enigma.enigma_shop.dto.response.CustomerResponse;
import com.enigma.enigma_shop.entity.Customer;

import java.util.Date;
import java.util.List;

public interface CustomerService {

    CustomerResponse create(Customer customer);
    CustomerResponse getOneById(String id);
    Customer getById(String id);

/*    List<Customer> getAll(String name, String phone, Date birth, Boolean status);*/
    List<CustomerResponse> getAll(SearchCustomerRequest request);
    CustomerResponse update(UpdateCustomerRequest request);

    void updateStatusById(String id, Boolean status);
    void deleteById(String id);

}
