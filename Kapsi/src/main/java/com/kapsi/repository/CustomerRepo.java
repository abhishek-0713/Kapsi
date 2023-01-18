package com.kapsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kapsi.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

}
