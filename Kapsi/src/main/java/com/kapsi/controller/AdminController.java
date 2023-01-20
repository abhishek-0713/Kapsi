package com.kapsi.controller;

import com.kapsi.exceptions.LogInException;
import com.kapsi.model.Admin;
import com.kapsi.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    /*--------------------------------------------   Admin Account  ------------------------------------------------*/
    @PostMapping("/add")
    public ResponseEntity<Admin> createAccount(@Valid @RequestBody Admin admin) throws ValidationException, LogInException {

        if (admin == null){
            throw new LogInException("Invalid Details");
        }
        return new ResponseEntity<Admin>(adminService.createAdminAccount(admin), HttpStatus.CREATED);
    }









}
