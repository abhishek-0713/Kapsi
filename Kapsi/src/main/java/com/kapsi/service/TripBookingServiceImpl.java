package com.kapsi.service;

import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapsi.exceptions.TripBookingException;
import com.kapsi.model.CurrentUserSession;
import com.kapsi.model.TripBooking;
import com.kapsi.repository.CurrentSessionRepo;
import com.kapsi.repository.TripBookingRepo;

@Service
public class TripBookingServiceImpl implements TripBookingService{
	
	@Autowired
	private TripBookingRepo tripBookingRepository;
	@Autowired
	private CurrentSessionRepo currentSessionRepo;

	@Override
	public TripBooking insertTripBooking(String key, TripBooking tripBooking) throws TripBookingException,LoginException{
		// TODO Auto-generated method stub
		CurrentUserSession currentUserSession=currentSessionRepo.findByUuid(key);
		if(currentUserSession==null) {
			throw new LoginException("Already Login");
			
		}else {
			tripBookingRepository.save(tripBooking);
			return tripBooking;
		}
		
	}

	@Override
	public TripBooking updateTripBooking(String key,TripBooking tripBooking) throws TripBookingException {
		// TODO Auto-generated method stub
		Optional<TripBooking> optional=tripBookingRepository.findById(tripBooking.getTripBookingId());
		if(optional.isPresent()) {
			return tripBookingRepository.save(tripBooking);
		}else {
			throw new TripBookingException("No Trip Booked with this id: "+tripBooking.getTripBookingId());
		}
		
	}

	@Override
	public TripBooking deleteTripBooking(String key,Integer tripBookingId) throws TripBookingException {
		// TODO Auto-generated method stub
		Optional<TripBooking> optional= tripBookingRepository.findById(tripBookingId);
		if(optional.isPresent()) {
			TripBooking tripBooking = optional.get();
			tripBookingRepository.delete(tripBooking);
			return tripBooking;
		}else {
			throw new TripBookingException("Invalid Trip Booking Id "+tripBookingId);
		}
		
	}

	@Override
	public List<TripBooking> viewAllTripsCustomer(String key,Integer customerId) throws TripBookingException {
		List<TripBooking> tripBookingList=tripBookingRepository.findAll();
		if(tripBookingList.isEmpty()) {
			 throw new TripBookingException("No Trip Booked ");
		}else {
			return tripBookingList;
		}
		
	}

	@Override
	public TripBooking calculateBill(String key,Integer customerId) throws TripBookingException {
		// TODO Auto-generated method stub
		return null;
	}
}
