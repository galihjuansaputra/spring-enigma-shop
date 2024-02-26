package com.enigma.enigma_shop.repository;

import com.enigma.enigma_shop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    /*
     *
     * 1. Nama
     * 2. Mobile Phone
     * 3. Birth Date
     * 4. Status
     * */

    List<Customer> findAllByNameContainingIgnoreCaseOrMobilePhoneNoOrBirthDateOrStatus(String name, String phone, Date birth, Boolean status);

}
