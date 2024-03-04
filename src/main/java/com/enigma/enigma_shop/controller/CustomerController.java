package com.enigma.enigma_shop.controller;

import com.enigma.enigma_shop.dto.request.SearchCustomerRequest;
import com.enigma.enigma_shop.dto.request.UpdateCustomerRequest;
import com.enigma.enigma_shop.dto.response.CustomerResponse;
import com.enigma.enigma_shop.entity.Customer;
import com.enigma.enigma_shop.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/customers")
public class CustomerController {
    private final CustomerService customerService;

/*    @PostMapping
    public ResponseEntity<Customer> createNewCustomer(@RequestBody Customer payload) {

        Customer customer = customerService.create(payload);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customer);
    }*/

    @GetMapping(path = "/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id) {
        Customer customer = customerService.getById(id);
        return ResponseEntity.ok(customer);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomer(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "mobilePhoneNo", required = false) String mobilePhoneNo,
            @RequestParam(name = "birthDate", required = false) Date birthDate,
            @RequestParam(name = "status", required = false) Boolean status,
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize

    ) {

        SearchCustomerRequest request = SearchCustomerRequest.builder()
                .name(name)
                .mobilePhoneNo(mobilePhoneNo)
                .birthDate(birthDate)
                .status(status)
                .pageNo(pageNo)
                .pageSize(pageSize)
                .build();

        List<CustomerResponse> customer = customerService.getAll(request);

        return ResponseEntity.ok(customer);
    }

    @PutMapping
    public ResponseEntity<CustomerResponse> updateCustomer(@RequestBody UpdateCustomerRequest payload) {
        CustomerResponse customer = customerService.update(payload);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStatusCustomer(
            @PathVariable(name = "id") String id,
            @RequestParam(name = "status") Boolean status
    ) {
        customerService.updateStatusById(id, status);
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        customerService.deleteById(id);
        return ResponseEntity.ok("OK");
    }

}
