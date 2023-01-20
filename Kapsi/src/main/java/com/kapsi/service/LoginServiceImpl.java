package com.kapsi.service;

import com.kapsi.exceptions.LogInException;
import com.kapsi.model.Admin;
import com.kapsi.model.CurrentUserSession;
import com.kapsi.model.Customer;
import com.kapsi.model.UserLogin;
import com.kapsi.repository.AdminRepo;
import com.kapsi.repository.CurrentSessionRepo;
import com.kapsi.repository.CustomerRepo;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private AdminRepo adminRepo;

	@Autowired
	private CurrentSessionRepo currentSessionRepo;

	@Autowired
	private CustomerRepo customerRepo;


	/*-------------------------------------------   Login   --------------------------------------------------*/
	@Override
	public String login(UserLogin userLogin) throws LogInException {

        Admin admin = adminRepo.findAdminByMobile(userLogin.getMobileNumber());
		Customer customer = customerRepo.findCustomerByMobile(userLogin.getMobileNumber());

//		Admin existing = admin.get(0);
//		Customer customer1 = customer.get(0);

		if(admin == null && customer == null ) {
			throw new LogInException("Invalid MobileNumber!");
		}

		// Validation Current User
		Optional<CurrentUserSession> optional;

		if (admin != null && customer == null){

			 optional =  currentSessionRepo.findByUserId(admin.getId());

			if(optional.isPresent()) {
				throw new LogInException("User Already Exists in the System.");
			}


			if(admin.getPassword().equals(userLogin.getPassword())) {

				String key= RandomString.make(6);

				CurrentUserSession currentUserSession = new CurrentUserSession(admin.getId(),key,LocalDateTime.now());

				currentSessionRepo.save(currentUserSession);

				return currentUserSession.toString();

			}

		}
		else {
			 optional =  currentSessionRepo.findByUserId(customer.getCustomerId());

			if(optional.isPresent()) {
				throw new LogInException("User Already Exists in the System.");
			}

			if (customer.getPassword().equals(userLogin.getPassword())){

				String key= RandomString.make(6);

				CurrentUserSession currentUserSession = new CurrentUserSession(customer.getCustomerId(),key,LocalDateTime.now());

				currentSessionRepo.save(currentUserSession);

				return currentUserSession.toString();
			}
		}


		throw new LogInException("Wrong password");
		
	}



	/*-------------------------------------   Logout   ----------------------------------------*/
	@Override
	public String logout(String key) throws LogInException {

		// Validation Current User
        CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
		if(currentUserSession == null) {
			throw new LogInException("Invalid Unique userId (Session Key).");
			
		}
		
		currentSessionRepo.delete(currentUserSession);
		
		return "Logged Out Successfully!";
	}

}
