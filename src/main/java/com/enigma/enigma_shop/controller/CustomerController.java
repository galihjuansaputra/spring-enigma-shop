package com.enigma.enigma_shop.controller;

import com.enigma.enigma_shop.entity.Customer;
import com.enigma.enigma_shop.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public Customer createNewCustomer(@RequestBody Customer customer) {
        return customerService.create(customer);
    }

    @GetMapping(path = "/{id}")
    public Customer getCustomerById(@PathVariable String id) {
        return customerService.getById(id);
    }

    @GetMapping
    public List<Customer> getAllCustomer(
            @RequestParam(name = "name", required = false ) String name,
            @RequestParam(name = "mobilePhoneNo", required = false ) String mobilePhoneNo,
            @RequestParam(name = "birthDate", required = false ) Date birthDate,
            @RequestParam(name = "status", required = false ) Boolean status
    ) {

        /*
        * 1. Nama
        * 2. Mobile Phone
        * 3. Birth Date
        * 4. Status
        * */

        return customerService.getAll(name,mobilePhoneNo,birthDate,status);
    }

    @PutMapping
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerService.update(customer);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable String id) {
        customerService.delete(id);
        return "OK";
    }

}
