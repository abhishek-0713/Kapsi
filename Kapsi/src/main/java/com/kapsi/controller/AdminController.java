package com.kapsi.controller;

import com.kapsi.exceptions.*;
import com.kapsi.model.*;
import com.kapsi.service.AdminService;

import com.kapsi.service.CabService;
import com.kapsi.service.CustomerService;
import com.kapsi.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private CabService cabService;

    /*********************************************** Admin services *******************************************************/

    /*--------------------------------------------   Create Admin Account  ------------------------------------------------*/
    @PostMapping("/create")
    public ResponseEntity<Admin> createAccount(@Valid @RequestBody Admin admin) throws ValidationException, LogInException, AdminException {

        if (admin == null){
            throw new ValidationException("Invalid Details");
        }
        return new ResponseEntity<Admin>(adminService.createAdminAccount(admin), HttpStatus.CREATED);
    }


    /*--------------------------------------------   Update Admin Account  ------------------------------------------------*/
    @PutMapping("/update")
    public ResponseEntity<Admin> updateAdminAccount(@RequestParam String key, @RequestParam String userName, @RequestBody Admin admin) throws AdminException, LogInException {

        Admin updateAdmin = adminService.updateAdminAccount(key, userName, admin);
        return new ResponseEntity<Admin>(updateAdmin, HttpStatus.ACCEPTED);

    }


    /*--------------------------------------------   Delete Admin Account  ------------------------------------------------*/
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAdmin(@RequestParam String key, @RequestParam String userName) throws AdminException, LogInException {

        String deleteAdmin = adminService.deleteAdminAccount(key, userName);
        return new ResponseEntity<>(deleteAdmin, HttpStatus.OK);
    }


    /*********************************************** Customer services *******************************************************/


    /*--------------------------------------------   All Customers  ------------------------------------------------*/
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers(@RequestParam String key) throws CustomerException, LogInException {

        return new ResponseEntity<List<Customer>>(customerService.getAllCustomer(key), HttpStatus.OK);
    }



    /*********************************************** Trip Booking services *******************************************************/


    /*--------------------------------------------   Get All Trips [ Customer ]  ------------------------------------------------*/
    @GetMapping("/trips/customer")
    public ResponseEntity<List<TripBooking>> getTripsByCustomer( @RequestParam String key, @RequestParam Integer customerId) throws CustomerException, TripBookingException, LogInException {

        List<TripBooking> tripBookings = adminService.getTripsByCustomer(key,customerId);
        return new ResponseEntity<>(tripBookings, HttpStatus.OK);

    }


    /*--------------------------------------------   Get All Trips [ Driver ]  ------------------------------------------------*/
    @GetMapping("/trips/driver")
    public ResponseEntity<List<TripBooking>> getTripsByDriver(@RequestParam String key, @RequestParam Integer driverId) throws TripBookingException, DriverException, LogInException {

        List<TripBooking> tripBookings = adminService.getTripsByDriver(key, driverId);
        return new ResponseEntity<>(tripBookings, HttpStatus.OK);

    }


    /*--------------------------------------------   Get All Trips  ------------------------------------------------*/
    @GetMapping("/trips")
    public ResponseEntity<List<TripBooking>> getAllTrips(@RequestParam String key) throws CustomerException, TripBookingException, LogInException {

        return new ResponseEntity<>(adminService.getAllTrips(key), HttpStatus.OK);
    }


    /*--------------------------------------------   Get All Trips [ Date ]  ------------------------------------------------*/
    @GetMapping("/trips/date")
    public ResponseEntity<List<TripBooking>> getTripsByDate(@RequestParam String key, @RequestParam String date)  throws TripBookingException, DriverException, LogInException {

        LocalDate localDate = LocalDate.parse(date);

        List<TripBooking> tripBookings = adminService.getTripsByDate(key, localDate);
        return new ResponseEntity<>(tripBookings, HttpStatus.OK);

    }


    /*--------------------------------------------   Get All Trips [ Between 2 Dates ]  ------------------------------------------------*/
    @GetMapping("/trips/betweenDays")
    public ResponseEntity<List<TripBooking>> getAllTripsBetweenDays(@RequestParam String key, @RequestParam Integer customerId, @RequestParam String startDate, @RequestParam String endDate) throws TripBookingException, DriverException, LogInException, CustomerException {

        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        List<TripBooking> tripBookings = adminService.getAllTripsBetweenDays(key, customerId, start, end);
        return new ResponseEntity<>(tripBookings, HttpStatus.OK);

    }




    /*********************************************** Driver services *******************************************************/


    /*--------------------------------------------   Get Driver  ------------------------------------------------*/
    @GetMapping("/driver/id/{driverId}")
    public ResponseEntity<Driver> getDriverHandler(@RequestParam String key, @PathVariable("driverId") Integer driverId) throws DriverException, LogInException{

        Driver driver = driverService.getDriverById(key,driverId);
        return new ResponseEntity<>(driver, HttpStatus.ACCEPTED);

    }


    /*--------------------------------------------   Get Driver [ Name ]  ------------------------------------------------*/
    @GetMapping("/driver/name/{userName}")
    public ResponseEntity<Driver> getDriverByNameHandler(@RequestParam String key, @PathVariable("userName") String userName) throws DriverException, LogInException{

        Driver driver = driverService.getDriverByName(key,userName);
        return new ResponseEntity<>(driver, HttpStatus.OK);

    }


    /*--------------------------------------------   Get All Drivers  ------------------------------------------------*/
    @GetMapping("/drivers")
    public ResponseEntity<List<Driver>> getAllDriverHandler(@RequestParam String key) throws DriverException, LogInException {

        List<Driver> allDriver = driverService.getAllDriver(key);
        return new ResponseEntity<>(allDriver, HttpStatus.OK);

    }



    /************************************************* Cab services *******************************************************/


    /*--------------------------------------------   Register Cab  ------------------------------------------------*/
    @PostMapping("/cab")
    public ResponseEntity<Cab> registerCab(@RequestBody Cab cab) throws DriverException{

        Cab registerCab = cabService.registerCab(cab);
        return new ResponseEntity<>(registerCab, HttpStatus.CREATED);

    }


    /*--------------------------------------------   Update Cab  ------------------------------------------------*/
    @PutMapping("/cab/update/{cabId}")
    public ResponseEntity<Cab> updateCabHandler(@RequestParam String key,@PathVariable("cabId") Integer cabId, @RequestBody Cab cab) throws DriverException, LogInException{

        Cab updateCab = cabService.updateCab(key,cabId, cab);
        return new ResponseEntity<>(updateCab,HttpStatus.ACCEPTED);

    }


    /*--------------------------------------------   Get All Cabs  ------------------------------------------------*/
    @GetMapping("/cabs")
    public ResponseEntity<List<Cab>> getAllCabHandler(@RequestParam String key) throws DriverException, LogInException {

        List<Cab> allCabs = cabService.getAllCabs(key);
        return new ResponseEntity<>(allCabs, HttpStatus.OK);

    }


    /*--------------------------------------------   Delete Cab  ------------------------------------------------*/
    @DeleteMapping("/cab/delete/{cabId}")
    public ResponseEntity<Cab> deleteCabHandler(@RequestParam String key,@PathVariable("cabId") Integer cabId) throws DriverException, LogInException{

        Cab deleteCab = cabService.deleteCab(key,cabId);
        return new ResponseEntity<>(deleteCab, HttpStatus.OK);

    }


    /*--------------------------------------------   Get Cab [ Driver ]  ------------------------------------------------*/
    @GetMapping("/cab/driver")
    public ResponseEntity<Driver> getCabDriverHandler(@RequestParam String key, @RequestParam Integer driverId) throws CabException, LogInException {

        Driver driver = cabService.viewDriverByCabId(key, driverId);
        return new ResponseEntity<>(driver, HttpStatus.OK);

    }


}
