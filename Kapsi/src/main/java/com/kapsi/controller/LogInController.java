package com.kapsi.controller;

import com.kapsi.exceptions.LogInException;
import com.kapsi.model.Customer;
import com.kapsi.model.UserLogin;
import com.kapsi.service.CustomerService;
import com.kapsi.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class LogInController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private CustomerService customerService;

    /*---------------------------------------------  login  ---------------------------------------------*/
    @PostMapping("/login")
    public ResponseEntity<String> loginMapping(@RequestBody UserLogin userLogin) throws LogInException {

        String output = loginService.login(userLogin);


        return new ResponseEntity<String>(output, HttpStatus.OK);
    }


//    /*---------------------------------------------  login  ---------------------------------------------*/
//    @PostMapping("/login/customer")
//    public ResponseEntity<String> customerLoginMapping(@RequestBody Customer userLogin) throws LogInException {
//
//        String output = loginService.loginCustomer(userLogin);
//
//        return new ResponseEntity<String>(output, HttpStatus.OK);
//    }


    /*---------------------------------------------  logout   ---------------------------------------------*/
    @PostMapping("/logout")
    public ResponseEntity<String> logoutMapping(@RequestParam String key) throws LogInException {

        String output = loginService.logout(key);

        return new ResponseEntity<String>(output,HttpStatus.OK);
    }
}
