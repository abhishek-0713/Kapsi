package com.kapsi.service;

import java.util.List;
import java.util.Optional;

import com.kapsi.exceptions.CabException;
import com.kapsi.repository.CabRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapsi.exceptions.DriverException;
import com.kapsi.model.Cab;
import com.kapsi.model.Driver;
import com.kapsi.repository.DriverRepo;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	private DriverRepo driverRepo;

	@Autowired
	private CabRepo cabRepo;
	
	@Override
	public Driver registerDriver(Driver driver) throws DriverException {
		
		Cab cab = new Cab();
		cab.setCarType(driver.getCab().getCarType());
		cab.setPerKmRate(driver.getCab().getPerKmRate());
		
		Driver driver1 = new Driver();

		driver1.setUserName(driver.getUserName());
		driver1.setPassword(driver.getPassword());
		driver1.setAddress(driver.getAddress());
		driver1.setEmail(driver.getEmail());
		driver1.setMobileNumber(driver.getMobileNumber());
		driver1.setLicenceNo(driver.getLicenceNo());
		driver1.setRating(driver.getRating());
		driver1.setCab(cab);
		cab.setDriver(driver1);
	
		return driverRepo.save(driver1);
	}

	@Override
	public Driver updateDriver(Integer driverId,Driver driver) throws DriverException {
		
		Optional<Driver> d = driverRepo.findById(driverId);
		
		if(d.isPresent()) {
			 Driver update = d.get();
			 driverRepo.save(update);
			 return update;
		}
		else
	        throw new DriverException("Driver Not Found By This Id :"+driverId);	
	}

	@Override
	public Driver getDriverById(Integer driverId) throws DriverException {
		
		Optional<Driver> dri = driverRepo.findById(driverId);
		
		if(dri.isPresent()) {
			return dri.get();
		}
		else
			throw new DriverException("Driver Not Found By This Id :"+driverId);	
	}

	@Override
	public Driver getDriverByName(String userName) throws DriverException {
		
		Optional<Driver> dri = driverRepo.getByName(userName);
		
		if(dri.isPresent()) {
			return dri.get();
		}
		
		else
			throw new DriverException("Driver Not Found By This Name :"+ userName);
	}

	@Override
	public Driver deleteDriverById(Integer driverId) throws DriverException {
		
		Optional<Driver> dri = driverRepo.findById(driverId);
		
		if(dri.isEmpty()){
			throw new DriverException("Driver Not Found By This Id :"+driverId);
		}
		 Driver d = dri.get();
		 driverRepo.delete(d);
		return d;
	}


	@Override
	public List<Driver> getAllDriver() throws DriverException {
		
		List<Driver> list = driverRepo.findAll();
		
		if(list.isEmpty()) {
			throw new DriverException("DriverData Not Found");
		}
		
		return list;
	}

//	@Override
//	public Driver getDriverByCab(String carType) throws DriverException {
//		
//		List<Driver> dri = dDao.getDriverByCab(carType);
//		
//		if(dri.isEmpty()) {
//			throw new DriverException("Drivers Not Found By This Cab :" + carType);
//		}
//		else
//		   
//			return dri.get(0);
//			
//		
//	}

	
	
}
