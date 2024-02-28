package com.enigma.enigma_shop.repository;

import com.enigma.enigma_shop.entity.Customer;
import jakarta.transaction.Transactional;
import org.aspectj.weaver.ast.Var;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, String>, JpaSpecificationExecutor<Customer> {

    @Modifying
    @Query(value = "UPDATE m_customer SET status = :status WHERE id = :id", nativeQuery = true)

    void updateStatus(@Param("id") String id, @Param("status") Boolean status);


    /*
     *
     * 1. Nama
     * 2. Mobile Phone
     * 3. Birth Date
     * 4. Status
     * */

    List<Customer> findAllByNameContainingIgnoreCaseOrMobilePhoneNoOrBirthDateOrStatus(String name, String phone, Date birth, Boolean status);

}
