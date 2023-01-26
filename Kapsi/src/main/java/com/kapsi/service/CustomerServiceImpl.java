package com.kapsi.service;

import com.kapsi.exceptions.CustomerException;
import com.kapsi.exceptions.DriverException;
import com.kapsi.exceptions.LogInException;
import com.kapsi.exceptions.TripBookingException;
import com.kapsi.model.CurrentUserSession;
import com.kapsi.model.Customer;
import com.kapsi.model.Driver;
import com.kapsi.model.TripBooking;
import com.kapsi.repository.CurrentSessionRepo;
import com.kapsi.repository.CustomerRepo;
import com.kapsi.repository.DriverRepo;
import com.kapsi.repository.TripBookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    private CustomerRepo customerRepo;

	@Autowired
	private CurrentSessionRepo currentSessionRepo;

	@Autowired
	private TripBookingRepo tripBookingRepo;

	@Autowired
	private DriverRepo driverRepo;


	/*-------------------------------- Add Customer Implementation ---------------------------------*/
	@Override
    public Customer insertCustomer(Customer customer) {

        return customerRepo.save(customer);
    }


	/*-------------------------------- Update Customer Implementation ---------------------------------*/
	@Override
    public Customer updateCustomer(String key, Customer customer, String mobileNumber) throws CustomerException, LogInException {

		CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
		if(currentUserSession == null) {
			throw new LogInException("No User LoggedIn");
		}

    	Customer updateCustomer = customerRepo.findCustomerByMobile(mobileNumber);
    	if (customer ==null) {
    	    throw new CustomerException("No customer Exist with the given mobile number");
    	}
		updateCustomer.setUserName(customer.getUserName());
		updateCustomer.setMobileNumber(customer.getMobileNumber());
		updateCustomer.setAddress(customer.getAddress());
		updateCustomer.setEmail(customer.getEmail());
    		
    		 return customerRepo.save(updateCustomer);
    	}


	/*-------------------------------- Delete Customer Implementation ---------------------------------*/
	@Override
    public Customer deleteCustomer(String key, Integer customerId) throws CustomerException, LogInException {

		CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
		if(currentUserSession == null) {
			throw new LogInException("No User LoggedIn");
		}


    	Optional<Customer> customer = customerRepo.findById(customerId);
    	
    	if (customer  ==null) {
    	    throw new CustomerException("No customer Exist with the given customer Id");
    	} 
    	
    	Customer customer2 = customer.get();
    	customerRepo.delete(customer2);
    	
    	return customer2;
    }


	/*-------------------------------- Gel All Customers Implementation ---------------------------------*/
	@Override
    public List<Customer> getAllCustomer(String key) throws CustomerException, LogInException {

		CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
		if(currentUserSession == null) {
			throw new LogInException("No User LoggedIn");
		}

    	List<Customer> customers = customerRepo.findAll();
    	if(customers.isEmpty()) {
    		throw new CustomerException("No Customer Exist");
    	}
    	
    	return customers;
    	
    }


	/*-------------------------------- View Customer Implementation ---------------------------------*/
	@Override
    public Customer viweCustomer(String key, Integer customerId) throws CustomerException, LogInException {

		CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
		if(currentUserSession == null) {
			throw new LogInException("No User LoggedIn");
		}

        Optional<Customer> customer = customerRepo.findById(customerId);
    	if (customer  == null) {
    	    throw new CustomerException("No customer Exist with the given customer Id");
    	} 
        return customer.get();
        
    }


	/*-------------------------------- Add Trip To Customer Implementation ---------------------------------*/
	@Override
	public TripBooking bookTrip(String key, Integer tripBookingId, Integer driverId) throws DriverException, LogInException {

		CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
		if(currentUserSession == null) {
			throw new LogInException("No User LoggedIn");
		}


		Optional<Driver> driver = driverRepo.findById(driverId);
		if (driver  == null) {
			throw new DriverException("No Driver Exist with the given Driver Id");
		}

		Optional<TripBooking> optional= tripBookingRepo.findById(tripBookingId);
		if(optional.isPresent()) {
			Driver driver1 = driver.get();
			TripBooking tripBooking = optional.get();

			tripBooking.setDriver(driver1);
			return tripBookingRepo.save(tripBooking);

		}

		throw new TripBookingException("Invalid Trip Booking Id "+tripBookingId);


	}


}












