package com.enigma.enigma_shop.specification;

import com.enigma.enigma_shop.dto.request.SearchCustomerRequest;
import com.enigma.enigma_shop.entity.Customer;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerSpecification {
    public static Specification<Customer> getSpecification(SearchCustomerRequest request) {
        return (root, cq, cb) -> {
            /*
             * 3 Object Criteria yang digunakan
             * 1. Criteria Builder -> untuk membangun query expression (<, >, like, <>, !=) dan membangun (query, update, delete)
             * 2. Criteria (Query(Select), Update, Delete) (Criteria Query) -> where, orderBy, having, groupBy
             * 3. Root -> merepresentasikan dari entity (table)
             * */

            List<Predicate> predicates = new ArrayList<>();

            if (request.getName() != null) {
                Predicate namePredicate = cb.like(cb.lower(root.get("name")), "%" + request.getName().toLowerCase() + "%");
                predicates.add(namePredicate);
            }

            if (request.getMobilePhoneNo() != null) {
                Predicate phonePredicate = cb.equal(root.get("mobilePhoneNo"), request.getMobilePhoneNo());
                predicates.add(phonePredicate);
            }

            if (request.getBirthDate() != null) {
                Predicate birthDatePredicate = cb.equal(root.get("birthDate"), request.getBirthDate());
                predicates.add(birthDatePredicate);
            }

            if (request.getStatus() != null) {
                Predicate statusPredicate = cb.equal(root.get("status"), request.getStatus());
                predicates.add(statusPredicate);
            }

            return cq.where(cb.or(predicates.toArray(new Predicate[]{}))).getRestriction();


        };
    }
}