package com.kapsi.controller;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kapsi.exceptions.TripBookingException;
import com.kapsi.model.TripBooking;
import com.kapsi.service.TripBookingService;


@RestController
public class TripBookingController {
	@Autowired
	private TripBookingService tripBookingService;
	
//	@PostMapping("/saveTrip")
//	public ResponseEntity<TripBooking> saveTrip(@RequestParam String Key,@RequestBody TripBooking tripBooking) throws TripBookingException, LoginException{
//		TripBooking tb=tripBookingService.insertTripBooking(Key,tripBooking);
//		
//		return new ResponseEntity<>(tb,HttpStatus.CREATED);
//		
//		
//		
//	}

}
