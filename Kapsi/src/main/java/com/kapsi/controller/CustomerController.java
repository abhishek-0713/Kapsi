package com.kapsi.controller;

import java.util.List;

import javax.security.auth.login.LoginException;
import javax.validation.Valid;
import javax.validation.ValidationException;

import com.kapsi.exceptions.DriverException;
import com.kapsi.exceptions.TripBookingException;
import com.kapsi.model.TripBooking;
import com.kapsi.service.TripBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kapsi.exceptions.CustomerException;
import com.kapsi.exceptions.LogInException;
import com.kapsi.model.Customer;
import com.kapsi.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    @Autowired
    private TripBookingService tripBookingService;


    /************************************************  Customer Services  ********************************************************/


    /*--------------------------------------------   Add Customer Account  ------------------------------------------------*/
    @PostMapping("/create")
    public ResponseEntity<Customer> createAccount(@Valid @RequestBody Customer customer) throws ValidationException, LogInException {

        if (customer == null){
            throw new LogInException("Invalid Details");
        }
        return new ResponseEntity<Customer>(customerService.insertCustomer(customer), HttpStatus.CREATED);
    }


    /*--------------------------------------------   Update Customer Account  ------------------------------------------------*/
    @PutMapping("/update")
    public ResponseEntity<Customer> updateCustomer(@RequestParam String key, @RequestBody Customer customer, @RequestParam String mobileNumber) throws CustomerException, LogInException {
    	
    	Customer updateCustomer = customerService.updateCustomer(key, customer, mobileNumber);
    	return new ResponseEntity<Customer>(updateCustomer, HttpStatus.ACCEPTED);
    }


    /*--------------------------------------------   View Customer Account  ------------------------------------------------*/
    @GetMapping("/details")
    public ResponseEntity<Customer> viewCustomer(@RequestParam String key,@RequestParam Integer customerId) throws CustomerException, LogInException {

        Customer customer = customerService.viweCustomer(key, customerId);
    	return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }


    /*--------------------------------------------   Delete Customer Account  ------------------------------------------------*/
    @DeleteMapping("/delete")
    public ResponseEntity<Customer> deleteCustomer(@RequestParam String key, @RequestParam Integer customerId) throws CustomerException, LogInException {
    	
    	Customer customer = customerService.deleteCustomer(key, customerId);
    	return new ResponseEntity<Customer>(customer, HttpStatus.OK);

    }



    /*************************************************  Trip-Booking Services  ********************************************************/


    /*--------------------------------------------   Create Trip   ------------------------------------------------*/
    @PostMapping("/trip/create")
    public ResponseEntity<TripBooking> saveTrip(@RequestParam String key, @RequestParam Integer customerId,@RequestBody TripBooking tripBooking) throws TripBookingException, LoginException{

        TripBooking insertTripBooking = tripBookingService.insertTripBooking(key,tripBooking,customerId);
        return new ResponseEntity<>(insertTripBooking, HttpStatus.CREATED);

    }


    /*--------------------------------------------   Update Trip   ------------------------------------------------*/
    @PutMapping("/trip/update")
    public ResponseEntity<TripBooking> updateTripBooking(@RequestParam String key, @RequestParam TripBooking tripBooking) throws LoginException {

        TripBooking updateTrip = tripBookingService.updateTripBooking(key, tripBooking);
        return new ResponseEntity<>(updateTrip, HttpStatus.ACCEPTED);

    }


    /*--------------------------------------------   Allocate Trip To Driver  ------------------------------------------------*/
    @PutMapping("/trip/allocate/driver")
    public ResponseEntity<TripBooking> allocateTripToDriver(@RequestParam String key, @RequestParam Integer tripBookingId, @RequestParam Integer driverId) throws CustomerException, LoginException, DriverException, LogInException {

        TripBooking allocateTripBooking = customerService.bookTrip(key,tripBookingId, driverId);
        return new ResponseEntity<>(allocateTripBooking, HttpStatus.OK);

    }


    /*--------------------------------------------   Delete Trip   ------------------------------------------------*/
    @DeleteMapping("/trip/delete")
    public ResponseEntity<TripBooking> deleteTripBooking(@RequestParam String key, @RequestParam Integer tripBookingId) throws LoginException {

        TripBooking tripBooking = tripBookingService.deleteTripBooking(key, tripBookingId);
        return new ResponseEntity<>(tripBooking, HttpStatus.OK);

    }


    /*--------------------------------------------   Get All Trips   ------------------------------------------------*/
    @GetMapping("/trips")
    public ResponseEntity<List<TripBooking>> viewAllTripsByCustomer(@RequestParam String key,@RequestParam Integer customerId) throws LoginException {

        List<TripBooking> tripBookings = tripBookingService.viewAllTripsByCustomer(key, customerId);
        return new ResponseEntity<>(tripBookings, HttpStatus.OK);

    }


    /*--------------------------------------------   Generate Trip Bill   ------------------------------------------------*/
    @GetMapping("/trip/bill")
    public ResponseEntity<String> calculateBill(@RequestParam String key, @RequestParam Integer customerId, @RequestParam Integer tripBookingId) throws CustomerException, LoginException {

        String tripBooking = tripBookingService.calculateBill(key, customerId, tripBookingId);
        return new ResponseEntity<>(tripBooking, HttpStatus.OK);
    }


}
