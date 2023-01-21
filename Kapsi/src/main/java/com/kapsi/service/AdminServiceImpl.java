package com.kapsi.service;

import com.kapsi.exceptions.LogInException;
import com.kapsi.model.Admin;
import com.kapsi.repository.AdminRepo;
import com.kapsi.repository.CurrentSessionRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private CurrentSessionRepo currentSessionRepo;


    @Override
    public Admin createAdminAccount(Admin admin) throws LogInException {

        Admin admins = adminRepo.findAdminByMobile(admin.getMobileNumber());

        if(admins == null) {

            return adminRepo.save(admin);
        }
        throw new LogInException("Duplicate Mobile Number [ Already Registered with different customer ] ");

    }



}
