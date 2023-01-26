package com.kapsi.service;

import java.util.List;
import java.util.Optional;

import com.kapsi.exceptions.CabException;
import com.kapsi.repository.CabRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapsi.exceptions.DriverException;
import com.kapsi.exceptions.LogInException;
import com.kapsi.model.Cab;
import com.kapsi.model.CurrentUserSession;
import com.kapsi.model.Driver;
import com.kapsi.repository.CurrentSessionRepo;
import com.kapsi.repository.DriverRepo;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	private DriverRepo driverRepo;
	
	@Autowired
	private CabRepo cabRepo;
	
	@Autowired
	private CurrentSessionRepo currentSessionRepo;


	/*-------------------------------- Add Driver Account Implementation ---------------------------------*/
	@Override
	public Driver registerDriver(Driver driver) throws DriverException {

		return driverRepo.save(driver);
	}


	/*-------------------------------- Update Driver Account Implementation ---------------------------------*/
	@Override
	public Driver updateDriver(String key, Integer driverId,Driver driver) throws DriverException, LogInException {

		CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
		if(currentUserSession == null) {
			throw new LogInException("No User LoggedIn");
		}
		
		
		Optional<Driver> optionalDriver = driverRepo.findById(driverId);
		
		if(optionalDriver.isPresent()) {
			 Driver update = optionalDriver.get();
			 driverRepo.save(update);
			 return update;
		}
		throw new DriverException("Driver Not Found By This Id :"+driverId);
	}


	/*-------------------------------- Update Driver Account Implementation ---------------------------------*/
	@Override
	public Driver getDriverById(String key,Integer driverId) throws DriverException, LogInException {

		CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
		if(currentUserSession == null) {
			throw new LogInException("No User LoggedIn");
		}
		
		Optional<Driver> optionalDriver = driverRepo.findById(driverId);
		
		if(optionalDriver.isPresent()) {
			return optionalDriver.get();
		}
		throw new DriverException("Driver Not Found By This Id :"+driverId);
	}


	/*-------------------------------- Get Driver Implementation [ Name ] ---------------------------------*/
	@Override
	public Driver getDriverByName(String key,String userName) throws DriverException, LogInException {

		CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
		if(currentUserSession == null) {
			throw new LogInException("No User LoggedIn");
		}
		
		Optional<Driver> optionalDriver = driverRepo.getByName(userName);
		
		if(optionalDriver.isPresent()) {
			return optionalDriver.get();
		}
		throw new DriverException("Driver Not Found By This Name :"+ userName);

	}


	/*-------------------------------- Delete Driver Account Implementation ---------------------------------*/
	@Override
	public Driver deleteDriverById(String key, Integer driverId) throws DriverException, LogInException {
		
		 CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
		 if(currentUserSession == null) {
			 throw new LogInException("No User LoggedIn");
		 }
		
		Optional<Driver> optionalDriver = driverRepo.findById(driverId);
		if(optionalDriver.isEmpty()){
			throw new DriverException("Driver Not Found By This Id :"+driverId);
		}
		 Driver deleteDriver = optionalDriver.get();
		 driverRepo.delete(deleteDriver);

		 return deleteDriver;

	}


	/*-------------------------------- Gel All Drivers Account Implementation ---------------------------------*/
	@Override
	public List<Driver> getAllDriver(String key) throws DriverException, LogInException {

		CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
		if(currentUserSession == null) {
			throw new LogInException("No User LoggedIn");
		}

		List<Driver> drivers = driverRepo.findAll();
		if(drivers.isEmpty()) {
			throw new DriverException("DriverData Not Found");
		}
		
		return drivers;
	}


	/*-------------------------------- Allocate Cab to Driver Account Implementation ---------------------------------*/
	@Override
	public Driver allocateCabToDriver(String key, Integer driverId, Integer cabId) throws DriverException, CabException, LogInException {

		CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
		if(currentUserSession == null) {
			throw new LogInException("No User LoggedIn");
		}

		 Optional<Cab> cab = cabRepo.findById(cabId);
		 Optional<Driver> driver = driverRepo.findById(driverId);
		 
		 if(cab.isPresent()) {
			 if(driver.isPresent()) {
		    
				  Driver d = driver.get();
				  Cab c = cab.get();
				  c.setDriver(d);
				  cabRepo.save(c);
				  d.setCab(c);
				  return driverRepo.save(d);
			 }
			 else {
				 throw new DriverException("Driver Not Found With This Id :"+ driverId);
			 }
		 }
		 else {
			 throw new CabException("Cab Not Found By This Id :"+ cabId);
		 }
		 		 
	}


	/*-------------------------------- View Cab [ DriverId ] Account Implementation ---------------------------------*/
	@Override
	public Cab viewCabByDriverId(String key, Integer driverId) throws DriverException, LogInException {

		CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
		if(currentUserSession == null) {
			throw new LogInException("No User LoggedIn");
		}

		Optional<Cab> cabOptional = cabRepo.findById(driverId);
		
		if(cabOptional.isPresent()) {
			return cabOptional.get();
		}
		throw new DriverException("Driver Not Found By This Id :" + driverId);
		
	}
	
}
