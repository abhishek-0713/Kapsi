package com.kapsi.service;

import com.kapsi.exceptions.*;
import com.kapsi.model.*;
import com.kapsi.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private CurrentSessionRepo currentSessionRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private TripBookingRepo tripBookingRepo;

    @Autowired
    private DriverRepo driverRepo;



    /*-------------------------------- Add Admin Implementation ---------------------------------*/
    @Override
    public Admin createAdminAccount(Admin admin) throws LogInException {

        Admin admins = adminRepo.findAdminByMobile(admin.getMobileNumber());

        if(admins == null) {
            return adminRepo.save(admin);
        }
        throw new LogInException("Duplicate Mobile Number [ Already Registered with different Admin ] ");

    }


    /*-------------------------------- Update Admin Implementation ---------------------------------*/
    @Override
    public Admin updateAdminAccount(String key, String userName, Admin admin) throws AdminException, LogInException {

        CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
        if(currentUserSession == null) {
            throw new LogInException("No User LoggedIn");
        }

        Admin updateAdmin = adminRepo.findByUserName(userName);
        if (updateAdmin == null) {
            throw new AdminException("Admin Does Not Exist with Given User Name");
        }

        updateAdmin.setEmail(admin.getEmail());
        updateAdmin.setUserName(admin.getUserName());
        updateAdmin.setPassword(admin.getPassword());
        updateAdmin.setMobileNumber(admin.getMobileNumber());
        updateAdmin.setAddress(admin.getAddress());

        return adminRepo.save(updateAdmin);
    }


    /*-------------------------------- Delete Admin Implementation ---------------------------------*/
    @Override
    public String deleteAdminAccount(String key, String userName) throws AdminException, LogInException {

        CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
        if(currentUserSession == null) {
            throw new LogInException("No User LoggedIn");
        }

        Admin deleteAdmin = adminRepo.findByUserName(userName);
        if (deleteAdmin == null) {
            throw new AdminException("Admin Does Not Exist with Given User Name");
        }

        adminRepo.delete(deleteAdmin);

        String adminDetails =  "{ " +
                "userName='" + userName + '\'' +
                ", password='" + deleteAdmin.getEmail() + '\'' +
                ", address='" + deleteAdmin.getUserName() + '\'' +
                ", mobileNumber='" + deleteAdmin.getMobileNumber() + '\'' +
                ", email='" + deleteAdmin.getAddress() + '\'' +
                '}';

        return "Admin Deleted Successfully With Given Details : " + adminDetails ;
    }


    /*-------------------------------- Get All Trips Implementation  ---------------------------------*/
    @Override
    public List<TripBooking> getTripsByCustomer(String key, Integer customerId) throws CustomerException, TripBookingException, LogInException {

        CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
        if(currentUserSession == null) {
            throw new LogInException("No User LoggedIn");
        }


        Optional<Customer> customer = customerRepo.findById(customerId);
        if (customer.isEmpty()){
            throw new CustomerException("Customer Does Not Exists With Customer ID : " + customerId);
        }

        List<TripBooking> tripBookings = customer.get().getTripBookings();
        if (tripBookings.isEmpty()){
            throw new TripBookingException("No Trip Booking Found For Customer");
        }

        return tripBookings;

    }


    /*-------------------------------- Get Trips By Driver Implementation ---------------------------------*/
    @Override
    public List<TripBooking> getTripsByDriver(String key, Integer driverId) throws TripBookingException, LogInException, DriverException {

        CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
        if(currentUserSession == null) {
            throw new LogInException("No User LoggedIn");
        }

        Optional<Driver> driver = driverRepo.findById(driverId);
        if (driver.isEmpty()){
            throw new DriverException("No Driver Found With Cab ID : " + driverId);
        }

        List<TripBooking> tripBookings = driver.get().getTripBookings();
        if (tripBookings.isEmpty()){
            throw new TripBookingException("No Trip Booking Found For Customer");
        }

        return tripBookings;
    }


    /*-------------------------------- Get Trips By Customer Implementation ---------------------------------*/
    @Override
    public List<TripBooking> getAllTrips() throws TripBookingException, CustomerException, LogInException {

        List<TripBooking> tripBookings = tripBookingRepo.findAll();
        if (tripBookings.isEmpty()){
            throw new TripBookingException("No Trip Booking Found");
        }

        return tripBookings;
    }


    /*-------------------------------- Get Trips By Date Implementation ---------------------------------*/
    @Override
    public List<TripBooking> getTripsByDate(String key, LocalDate date) throws TripBookingException, LogInException {

        CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
        if(currentUserSession == null) {
            throw new LogInException("No User LoggedIn");
        }

        List<TripBooking> tripBookings = tripBookingRepo.findByFromDateTime(date);
        if (tripBookings.isEmpty()){
            throw new TripBookingException("No Trip Booking Found On Date : " + date);
        }

        return tripBookings;
    }


    /*-------------------------------- Get Trips Between Date Implementation ---------------------------------*/
    @Override
    public List<TripBooking> getAllTripsBetweenDays(String key, Integer customerId, LocalDate startDate, LocalDate endDate) throws TripBookingException, LogInException, CustomerException {

        CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
        if(currentUserSession == null) {
            throw new LogInException("No User LoggedIn");
        }

        Optional<Customer> customer = customerRepo.findById(customerId);
        if (customer.isEmpty()){
            throw new CustomerException("Customer Does Not Exists With Customer ID : " + customerId);
        }


        LocalDate localDate = LocalDate.now();
        if(startDate.isAfter(localDate)){
            throw new TripBookingException("Invalid Start Date [ Future Date ]");
        }
        if(endDate.isAfter(localDate)){
            throw new TripBookingException("Invalid End Date [ Future Date ]");
        }
        if(startDate.isAfter(endDate)) {
            throw new TripBookingException("Invalid Start Date ");
        }


        List<TripBooking> tripBookings = customer.get().getTripBookings();
        tripBookings = tripBookingRepo.findByFromDateTimeBetween(startDate, endDate);
        if(tripBookings.isEmpty()){
            throw new TripBookingException("No Trip Booking to Show");
        }

        return tripBookings;

    }


}
