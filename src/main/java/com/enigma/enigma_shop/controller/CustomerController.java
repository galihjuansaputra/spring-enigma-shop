package com.enigma.enigma_shop.controller;

import com.enigma.enigma_shop.dto.request.SearchCustomerRequest;
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
            @RequestParam(name = "status", required = false ) Boolean status,
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize

    ) {

        SearchCustomerRequest request = SearchCustomerRequest.builder()
                .name(name)
                .mobilePhoneNo(mobilePhoneNo)
                .birthDate(birthDate)
                .status(status)
                .build();

        return customerService.getAll(request,pageNo,pageSize);
    }

    @PutMapping
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerService.update(customer);
    }

    @PutMapping("/{id}")
    public String updateStatusCustomer(
            @PathVariable(name = "id") String id,
            @RequestParam(name = "status") Boolean status
    ) {
        customerService.updateStatusById(id,status);
        return "OK";
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable String id) {
        customerService.deleteById(id);
        return "OK";
    }

}
