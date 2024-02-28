package com.enigma.enigma_shop.services.impl;

import com.enigma.enigma_shop.dto.request.SearchCustomerRequest;
import com.enigma.enigma_shop.entity.Customer;
import com.enigma.enigma_shop.repository.CustomerRepository;
import com.enigma.enigma_shop.services.CustomerService;
import com.enigma.enigma_shop.specification.CustomerSpecification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer create(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public Customer getById(String id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isEmpty()) throw new RuntimeException("Customer Not Found");
        return optionalCustomer.get();
    }

    @Override
    public List<Customer> getAll(SearchCustomerRequest request, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Customer> customers = customerRepository.findAll(pageable);
        List<Customer> listOfCustomer = customers.getContent();

        if (request.getName() != null || request.getMobilePhoneNo() != null || request.getBirthDate() != null || request.getStatus() != null) {
            Specification<Customer> specification = CustomerSpecification.getSpecification(request);
            return customerRepository.findAll(specification);
        }
        return listOfCustomer;

/*        if (name != null || phone != null || birth != null || status != null) {
            return customerRepository.findAllByNameContainingIgnoreCaseOrMobilePhoneNoOrBirthDateOrStatus(name, phone, birth, status);
        }
        return customerRepository.findAll();*/

    }

    @Override
    public Customer update(Customer customer) {
        getById(customer.getId());
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public void updateStatusById(String id, Boolean status) {
        findByIdOrThrowNotFound(id);
        customerRepository.updateStatus(id,status);
    }

    @Override
    public void deleteById(String id) {
        Customer currentCustomer = findByIdOrThrowNotFound(id);
        customerRepository.delete(currentCustomer);
    }

    public Customer findByIdOrThrowNotFound(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer Not Found"));
    }

}
