package com.kapsi.service;

import java.util.List;

import javax.security.auth.login.LoginException;

import com.kapsi.exceptions.TripBookingException;
import com.kapsi.model.TripBooking;

public interface TripBookingService {
  public TripBooking insertTripBooking(String Key,TripBooking tripBooking) throws TripBookingException,LoginException;
  public TripBooking updateTripBooking(String Key,TripBooking tripBooking) throws TripBookingException;
  public TripBooking deleteTripBooking(String Key,Integer tripBookingId) throws TripBookingException;
  public List<TripBooking> viewAllTripsCustomer(String Key,Integer customerId) throws TripBookingException;
  public TripBooking calculateBill(String Key,Integer customerId) throws TripBookingException;
  
}
