package com.aplikacja.herbaciarnia.repository;

import com.aplikacja.herbaciarnia.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long > {
}
