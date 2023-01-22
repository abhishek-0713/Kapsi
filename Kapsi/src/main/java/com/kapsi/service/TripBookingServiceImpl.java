package com.kapsi.service;

import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kapsi.exceptions.CustomerException;
import com.kapsi.exceptions.TripBookingException;
import com.kapsi.model.Cab;
import com.kapsi.model.CurrentUserSession;
import com.kapsi.model.Customer;
import com.kapsi.model.Driver;
import com.kapsi.model.TripBooking;
import com.kapsi.repository.CurrentSessionRepo;
import com.kapsi.repository.CustomerRepo;
import com.kapsi.repository.TripBookingRepo;

@Service
public class TripBookingServiceImpl implements TripBookingService{
	
	@Autowired
	private TripBookingRepo tripBookingRepository;
	
	@Autowired
	private CurrentSessionRepo currentSessionRepo;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	

	@Override
	public TripBooking insertTripBooking(String key, TripBooking tripBooking,Integer customerId) throws TripBookingException,LoginException{
		// TODO Auto-generated method stub
		CurrentUserSession currentUserSession=currentSessionRepo.findByUuid(key);
		TripBooking tr=null;
		if(currentUserSession==null) {
			throw new LoginException("No User Login");
			
		}else {
			Optional<Customer> customer=customerRepo.findById(customerId);
			if(customer.isPresent()) {
			List<TripBooking>bookings =customer.get().getTripBookings();
			tr=bookings.get(bookings.size()-1);
			if(bookings.size()>0) {
			  if(tr.getStatus()==false) {
				throw new TripBookingException("Cannot Book another ... As trip is already in Progress");
			   }
			}
			
			tripBooking.setCustomer(tr.getCustomer());
			tripBookingRepository.save(tripBooking);
		}
		}
		if(tr==null) {
			throw new TripBookingException("Not able to add trip");
		}else {
		return tr;
		}
		
		
	}

	@Override
	public TripBooking updateTripBooking(String key,TripBooking tripBooking) throws TripBookingException,LoginException {
		// TODO Auto-generated method stub
		CurrentUserSession currentUserSession=currentSessionRepo.findByUuid(key);
		if(currentUserSession==null) {
			throw new LoginException("No User Login");
			
		}else {
		Optional<TripBooking> optional=tripBookingRepository.findById(tripBooking.getTripBookingId());
		if(optional.isPresent()) {
			return tripBookingRepository.save(tripBooking);
		}else {
			throw new TripBookingException("No Trip Booked with this id: "+tripBooking.getTripBookingId());
		}
		}
	}

	@Override
	public TripBooking deleteTripBooking(String key,Integer tripBookingId) throws TripBookingException,LoginException {
		// TODO Auto-generated method stub
		CurrentUserSession currentUserSession=currentSessionRepo.findByUuid(key);
		if(currentUserSession==null) {
			throw new LoginException("No User Login");
			
		}else {
		Optional<TripBooking> optional= tripBookingRepository.findById(tripBookingId);
		if(optional.isPresent()) {
			TripBooking tripBooking = optional.get();
			tripBookingRepository.delete(tripBooking);
			return tripBooking;
		}else {
			throw new TripBookingException("Invalid Trip Booking Id "+tripBookingId);
		}
		}
		
	}

	@Override
	public List<TripBooking> viewAllTripsCustomer(String key,Integer customerId) throws TripBookingException,LoginException {
		CurrentUserSession currentUserSession=currentSessionRepo.findByUuid(key);
		List<TripBooking> tripBookingList=null;
		if(currentUserSession==null) {
			throw new LoginException("No User Login");
			
		}else {
			Optional<Customer> op=customerRepo.findById(customerId);
			 if(op.isPresent()) {
		      tripBookingList=op.get().getTripBookings();
		    }
		}
		if(tripBookingList.isEmpty()) {
			throw new TripBookingException("No trip Found");
		}else {
			return tripBookingList;
		}
		
	}


	@Override
	public TripBooking calculateBill(String key,Integer customerId) throws TripBookingException,LoginException,CustomerException {
		// TODO Auto-generated method stub
		CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
		
		TripBooking tripBooking=null;
		if(currentUserSession==null) {
			throw new LoginException("No user Login");
		}else {
			Optional<Customer> optional=customerRepo.findById(customerId);
		if(optional.isPresent()) {
			List<TripBooking> booking=optional.get().getTripBookings();
			if(booking.size()>0) {
			tripBooking=booking.get(booking.size()-1);
			if(tripBooking.getStatus()==true) {
				Driver driver=tripBooking.getDriver();
				Cab cab=driver.getCab();
				tripBooking.setBill(tripBooking.getDistanceInKm()*cab.getPerKmRate());
			}
			}
			
			
		}else {
			throw new CustomerException("Customer Not Found With Id "+customerId);
		}
		}
		if(tripBooking==null) {
			 throw new TripBookingException("No Trip Found");
		}else {
			return tripBooking;
		}
	}
		
}

