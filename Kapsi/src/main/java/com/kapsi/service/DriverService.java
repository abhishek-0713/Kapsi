package com.kapsi.service;

import java.util.List;

import com.kapsi.exceptions.CabException;
import com.kapsi.exceptions.DriverException;
import com.kapsi.exceptions.LogInException;
import com.kapsi.model.Cab;
import com.kapsi.model.Driver;

public interface DriverService {

	public Driver registerDriver(Driver driver)throws DriverException;
	
	public Driver updateDriver(String key,Integer driverId,Driver driver)throws DriverException, LogInException;
	
	public Driver getDriverById(String key,Integer driverId)throws DriverException, LogInException;
	
	public Driver getDriverByName(String key, String userName)throws DriverException, LogInException;
	
	public Driver allocateCabToDriver(Integer driverId,Integer cabId)throws DriverException, CabException;
	
	public Cab viewCabByDriverId(Integer DriverId)throws DriverException;
	
	public List<Driver> getAllDriver()throws DriverException;
	
	public Driver deleteDriverById(String key, Integer driverId)throws DriverException, LogInException;

}
