package com.kapsi.service;

import com.kapsi.model.Customer;
import com.kapsi.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    private CustomerRepo customerRepo;
    @Override
    public Customer insertCustomer(Customer customer) {

        return customerRepo.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return null;
    }

    @Override
    public Customer deleteCustomer(Integer customerId) {
        return null;
    }

    @Override
    public List<Customer> insertCustomer() {
        return null;
    }

    @Override
    public Customer viweCustomer(Integer customerId) {
        return null;
    }

    @Override
    public Customer validateCustomer(String username, String password) {
        return null;
    }
}
