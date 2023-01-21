package com.kapsi.service;

import com.kapsi.exceptions.CustomerException;
import com.kapsi.model.Customer;
import com.kapsi.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    private CustomerRepo customerRepo;
    
    @Override
    public Customer insertCustomer(Customer customer) {

        return customerRepo.save(customer);
    }

    @Override
    
    public Customer updateCustomer(Customer customer, String mobileNumber) throws CustomerException {
      
    	Customer customer1 = customerRepo.findCustomerByMobile(mobileNumber);
    	
    	if (customer ==null) {
    	    throw new CustomerException("No customer Exist with the given mobile number");
    	} 
    		customer1.setUserName(customer.getUserName());
    		customer1.setMobileNumber(customer.getMobileNumber());
    		customer1.setAddress(customer.getAddress());
    		customer1.setEmail(customer.getEmail());
    		
    		 return customerRepo.save(customer1);
    	}
    	
    

    @Override
    public Customer deleteCustomer(Integer customerId) throws CustomerException {
        
    	Optional<Customer> customer = customerRepo.findById(customerId);
    	
    	if (customer  ==null) {
    	throw new CustomerException("No customer Exist with the given customer Id");
    	} 
    	
    	Customer customer2 = customer.get();
    	customerRepo.delete(customer2);
    	
    	return customer2;
    }

    @Override
    public List<Customer> getAllCustomer() throws CustomerException {
      
    	List<Customer> customers = customerRepo.findAll();
    	
    	if(customers.isEmpty()) {
    		throw new CustomerException("No Customer Exist");
    	}
    	
    	return customers;
    	
    }

    @Override
    public Customer viweCustomer(Integer customerId) throws CustomerException {
    	
        Optional<Customer> customer = customerRepo.findById(customerId);
    	
    	if (customer  == null) {
    	    throw new CustomerException("No customer Exist with the given customer Id");
    	} 
        return customer.get();
        
    }

//    @Override
//    public Customer validateCustomer(String username, String password) {
//        return null;
//    }
    
}












