package com.kapsi.controller;

import com.kapsi.exceptions.LogInException;
import com.kapsi.model.Admin;
import com.kapsi.model.Customer;
import com.kapsi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
@RequestMapping("/customer")
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    /*--------------------------------------------   Admin Account  ------------------------------------------------*/
    @PostMapping("/add")
    public ResponseEntity<Customer> createAccount(@Valid @RequestBody Customer customer) throws ValidationException, LogInException {

        if (customer == null){
            throw new LogInException("Invalid Details");
        }
        return new ResponseEntity<Customer>(customerService.insertCustomer(customer), HttpStatus.CREATED);
    }
}
