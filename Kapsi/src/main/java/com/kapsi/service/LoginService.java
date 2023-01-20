package com.kapsi.service;

import com.kapsi.exceptions.LogInException;
import com.kapsi.model.Customer;
import com.kapsi.model.UserLogin;


public interface LoginService {

    public String login (UserLogin userLogin) throws LogInException;

    public String logout (String Key) throws LogInException;

}
