package com.kapsi.service;

import java.util.List;

import com.kapsi.exceptions.CustomerException;
import com.kapsi.exceptions.DriverException;
import com.kapsi.exceptions.LogInException;
import com.kapsi.model.Customer;
import com.kapsi.model.TripBooking;

import javax.security.auth.login.LoginException;

public interface CustomerService {

	public Customer insertCustomer(Customer customer);
	
	public Customer updateCustomer(String key, Customer customer, String mobileNumber) throws CustomerException, LogInException;
	
	public Customer deleteCustomer(String key, Integer customerId) throws CustomerException, LogInException;
	
	public List<Customer> getAllCustomer(String key) throws CustomerException, LogInException;
	
	public Customer viweCustomer(String key, Integer customerId) throws CustomerException, LogInException;

	TripBooking bookTrip(String key, Integer tripBookingId, Integer driverId) throws DriverException, LogInException;

}
