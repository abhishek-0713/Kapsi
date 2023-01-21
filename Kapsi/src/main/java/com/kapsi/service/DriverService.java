package com.kapsi.service;

import java.util.List;

import com.kapsi.exceptions.DriverException;
import com.kapsi.model.Driver;

public interface DriverService {

	public Driver registerDriver(Driver driver)throws DriverException;
	
	public Driver updateDriver(Integer driverId,Driver driver)throws DriverException;
	
	public Driver getDriverById(Integer driverId)throws DriverException;
	
	public Driver getDriverByName(String userName)throws DriverException;
	
	public List<Driver> getAllDriver()throws DriverException;
	
//	public Driver getDriverByCab(String carType)throws DriverException;
	
	public Driver deleteDriverById(Integer driverId)throws DriverException;
}
