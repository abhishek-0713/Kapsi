package com.kapsi.service;

import com.kapsi.exceptions.LogInException;
import com.kapsi.model.Admin;


import java.util.List;

public interface AdminService {

    /*-------------------------------- Add Admin  ---------------------------------*/
    public Admin createAdminAccount(Admin admin) throws LogInException;

}
