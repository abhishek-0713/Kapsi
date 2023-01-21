package com.kapsi.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.ValidationException;

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

    /*--------------------------------------------   Add Customer Account  ------------------------------------------------*/
    @PostMapping("/add")
    public ResponseEntity<Customer> createAccount(@Valid @RequestBody Customer customer) throws ValidationException, LogInException {

        if (customer == null){
            throw new LogInException("Invalid Details");
        }
        return new ResponseEntity<Customer>(customerService.insertCustomer(customer), HttpStatus.CREATED);
    }
    
    
    @PutMapping("/update")
    public ResponseEntity<Customer> updateCustomer( @RequestBody Customer customer, @RequestParam String mobileNumber) throws CustomerException{
    	
    	Customer customer2 = customerService.updateCustomer(customer, mobileNumber);
    	return new ResponseEntity<Customer>(customer2, HttpStatus.ACCEPTED);
    }
    
    
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() throws CustomerException{
    	
    	return new ResponseEntity<List<Customer>>(customerService.getAllCustomer(), HttpStatus.OK);
    }
    
    @GetMapping("/details")
    public ResponseEntity<Customer> viweCustomer(@RequestParam Integer customerId) throws CustomerException{
    	
    	return new ResponseEntity<Customer>(customerService.viweCustomer(customerId), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<Customer> deleteCustomer(@RequestParam Integer customerId) throws CustomerException{
    	
    	Customer customer = customerService.deleteCustomer(customerId);
    	
    	return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    	
 	 
    }
    
    
}
