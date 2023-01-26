package com.kapsi.service;

import java.util.List;
import java.util.Optional;

import com.kapsi.exceptions.CabException;
import com.kapsi.repository.CabRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapsi.exceptions.CabException;
import com.kapsi.exceptions.DriverException;
import com.kapsi.exceptions.LogInException;
import com.kapsi.model.Cab;
import com.kapsi.model.CurrentUserSession;
import com.kapsi.model.Driver;
import com.kapsi.repository.CabRepo;
import com.kapsi.repository.CurrentSessionRepo;
import com.kapsi.repository.DriverRepo;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	private DriverRepo driverRepo;

	@Autowired
	private CabRepo cabRepo;
	
	@Autowired
	private CabRepo cDao;
	
	@Autowired
	private CurrentSessionRepo currentSessionRepo;
	
	@Override
	public Driver registerDriver(Driver driver) throws DriverException {
  
		return driverRepo.save(driver1);

	}

	@Override
	public Driver updateDriver(String key, Integer driverId,Driver driver) throws DriverException, LogInException {
		
		 CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
	        if(currentUserSession == null) {
	            throw new LogInException("No User LoggedIn");
	        }
		
		
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
	public Driver getDriverById(String key,Integer driverId) throws DriverException, LogInException {
		
		 CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
	        if(currentUserSession == null) {
	            throw new LogInException("No User LoggedIn");
	        }
		
		Optional<Driver> dri = driverRepo.findById(driverId);
		
		if(dri.isPresent()) {
			return dri.get();
		}
		else
			throw new DriverException("Driver Not Found By This Id :"+driverId);	
	}

	@Override
	public Driver getDriverByName(String key,String userName) throws DriverException, LogInException {
		
		 CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
	        if(currentUserSession == null) {
	            throw new LogInException("No User LoggedIn");
	        }
		
		Optional<Driver> dri = driverRepo.getByName(userName);
		
		if(dri.isPresent()) {
			return dri.get();
		}
		
		else
			throw new DriverException("Driver Not Found By This Name :"+ userName);
	}

	@Override
	public Driver deleteDriverById(String key, Integer driverId) throws DriverException, LogInException{
		
		 CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
	        if(currentUserSession == null) {
	            throw new LogInException("No User LoggedIn");
	        }
		
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

	@Override
	public Driver allocateCabToDriver(Integer driverId, Integer cabId) throws DriverException, CabException {
		
		 Optional<Cab> cab = cDao.findById(cabId);
		 Optional<Driver> driver = dDao.findById(driverId);
		 
		 if(cab.isPresent()) {
			 if(driver.isPresent()) {
		    
				  Driver d = driver.get();
				  Cab c = cab.get();
				  c.setDriver(d);
				  cDao.save(c);
				  d.setCab(c);
				  return dDao.save(d);
			 }
			 else {
				 throw new DriverException("Driver Not Found With This Id :"+ driverId);
			 }
		 }
		 else {
			 throw new CabException("Cab Not Found By This Id :"+ cabId);
		 }
		 		 
	}

	@Override
	public Cab viewCabByDriverId(Integer driverId) throws DriverException {
		
		Optional<Cab> d = cDao.findById(driverId);
		
		if(d.isPresent()) {
			return d.get();
		}
		else
			throw new DriverException("Driver Not Found By This Id :" + driverId);
		
	}

	
}
