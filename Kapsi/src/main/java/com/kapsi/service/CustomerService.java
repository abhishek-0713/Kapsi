package com.kapsi.service;

import java.util.List;

import com.kapsi.model.Customer;

public interface CustomerService {

	public Customer insertCustomer(Customer customer);
	
	public Customer updateCustomer(Customer customer);
	
	public Customer deleteCustomer(Integer customerId);
	
	public List<Customer> insertCustomer();
	
	public Customer viweCustomer(Integer customerId);
	
	public Customer validateCustomer(String username, String password);
	
}
