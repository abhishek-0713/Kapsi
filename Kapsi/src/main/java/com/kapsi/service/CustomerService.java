package com.kapsi.service;

import java.util.List;

import com.kapsi.exceptions.CustomerException;
import com.kapsi.model.Customer;

public interface CustomerService {

	public Customer insertCustomer(Customer customer);
	
	public Customer updateCustomer(Customer customer,String mobileNumber) throws CustomerException;
	
	public Customer deleteCustomer(Integer customerId) throws CustomerException;
	
	public List<Customer> getAllCustomer() throws CustomerException;
	
	public Customer viweCustomer(Integer customerId) throws CustomerException;
	
//	public Customer validateCustomer(String username, String password);
	
}
