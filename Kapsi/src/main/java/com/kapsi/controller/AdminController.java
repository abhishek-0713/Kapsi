package com.kapsi.controller;

import com.kapsi.exceptions.*;
import com.kapsi.model.Admin;
import com.kapsi.model.TripBooking;
import com.kapsi.service.AdminService;

import com.kapsi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;



    /*--------------------------------------------   Admin Account  ------------------------------------------------*/
    @PostMapping("/add")
    public ResponseEntity<Admin> createAccount(@Valid @RequestBody Admin admin) throws ValidationException, LogInException, AdminException {

        if (admin == null){
            throw new ValidationException("Invalid Details");
        }
        return new ResponseEntity<Admin>(adminService.createAdminAccount(admin), HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public ResponseEntity<Admin> updateAdminAccount(@RequestParam String key, @RequestParam String userName, @RequestBody Admin admin) throws AdminException, LogInException {

        Admin updateAdmin = adminService.updateAdminAccount(key, userName, admin);
        return new ResponseEntity<Admin>(updateAdmin, HttpStatus.ACCEPTED);

    }


    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAdmin(@RequestParam String key, @RequestParam String userName) throws AdminException, LogInException {

        String deleteAdmin = adminService.deleteAdminAccount(key, userName);
        return new ResponseEntity<>(deleteAdmin, HttpStatus.OK);
    }


    @GetMapping("/trips/customer")
    public ResponseEntity<List<TripBooking>> getTripsByCustomer( @RequestParam String key, @RequestParam Integer customerId) throws CustomerException, TripBookingException, LogInException {

        List<TripBooking> tripBookings = adminService.getTripsByCustomer(key,customerId);
        return new ResponseEntity<>(tripBookings, HttpStatus.OK);

    }

    @GetMapping("/trips/driver")
    public ResponseEntity<List<TripBooking>> getTripsByDriver(@RequestParam String key, @RequestParam Integer driverId) throws TripBookingException, DriverException, LogInException {

        List<TripBooking> tripBookings = adminService.getTripsByDriver(key, driverId);
        return new ResponseEntity<>(tripBookings, HttpStatus.OK);

    }


    @GetMapping("/trips")
    public ResponseEntity<List<TripBooking>> getAllTrips() throws CustomerException, TripBookingException, LogInException {

        return new ResponseEntity<>(adminService.getAllTrips(), HttpStatus.OK);
    }


    @GetMapping("/trips/date")
    public ResponseEntity<List<TripBooking>> getTripsByDate(@RequestParam String key, @RequestParam String date)  throws TripBookingException, DriverException, LogInException {

        LocalDate localDate = LocalDate.parse(date);

        List<TripBooking> tripBookings = adminService.getTripsByDate(key, localDate);
        return new ResponseEntity<>(tripBookings, HttpStatus.OK);

    }


    @GetMapping("/trips/betweenDays")
    public ResponseEntity<List<TripBooking>> getAllTripsBetweenDays(@RequestParam String key, @RequestParam Integer customerId, @RequestParam String startDate, @RequestParam String endDate) throws TripBookingException, DriverException, LogInException, CustomerException {

        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        List<TripBooking> tripBookings = adminService.getAllTripsBetweenDays(key, customerId, start, end);
        return new ResponseEntity<>(tripBookings, HttpStatus.OK);

    }

}
