package com.kapsi.service;

import com.kapsi.exceptions.*;
import com.kapsi.model.Admin;
import com.kapsi.model.TripBooking;


import java.time.LocalDate;
import java.util.List;

public interface AdminService {

    /*-------------------------------- Add Admin  ---------------------------------*/
    public Admin createAdminAccount(Admin admin) throws AdminException, LogInException;


    /*-------------------------------- Update Admin  ---------------------------------*/
    public Admin updateAdminAccount(String key, String userName, Admin admin) throws AdminException, LogInException;


    /*-------------------------------- Delete Admin ---------------------------------*/
    String deleteAdminAccount(String key, String userName) throws AdminException, LogInException;


    /*-------------------------------- Get All Trips   ---------------------------------*/
    public List<TripBooking> getTripsByCustomer(String key, Integer customerId) throws CustomerException, TripBookingException, LogInException;


    /*-------------------------------- Get Trips By Driver  ---------------------------------*/
    List<TripBooking> getTripsByDriver(String key, Integer driverId) throws TripBookingException, LogInException, DriverException;


    /*-------------------------------- Get Trips By Customer  ---------------------------------*/
    public List<TripBooking> getAllTrips(String key) throws TripBookingException, CustomerException, LogInException;



    /*-------------------------------- Get Trips By Date  ---------------------------------*/
    List<TripBooking> getTripsByDate(String key, LocalDate date) throws TripBookingException, LogInException;




    /*-------------------------------- Get Trips Between Date  ---------------------------------*/
    List<TripBooking> getAllTripsBetweenDays(String key, Integer customerId, LocalDate from_date, LocalDate to_date) throws TripBookingException, LogInException, CustomerException;

}
