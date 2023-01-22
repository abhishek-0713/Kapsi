package com.kapsi.service;

import java.util.List;
import java.util.Optional;

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
public class CabServiceImpl implements CabService {

	@Autowired
	private CabRepo cDao;
	
	@Autowired
	private DriverRepo dDao;
	
	@Autowired
	private CurrentSessionRepo currentSessionRepo;
	

	@Override
	public Cab registerCab(Cab cab) throws DriverException {
		
		Cab c = cDao.save(cab);
		
		return c;
	}

	@Override
	public Cab updateCab(String key,Integer cabId, Cab cab) throws DriverException, LogInException {
		
		 CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
	        if(currentUserSession == null) {
	            throw new LogInException("No User LoggedIn");
	        }
		
		Optional<Cab> c = cDao.findById(cabId);
		
		if(c.isPresent()) {
			Cab updateCab =  c.get();
			cDao.save(updateCab);
			return updateCab;
		}
		else 
			
		   throw new DriverException("Cab Not Found By This Id :" + cabId);
		
	}

	@Override
	public List<Cab> getAllCabs() throws DriverException {
		
		List<Cab> all = cDao.findAll();
		
		if(all.isEmpty()) {
			throw new DriverException("Cabs Data Not Found :");
		}
		
		return all;
	}

	@Override
	public Cab deleteCab(String key,Integer cabId) throws DriverException, LogInException {
		
		 CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
	        if(currentUserSession == null) {
	            throw new LogInException("No User LoggedIn");
	        }
		
		Optional<Cab> c = cDao.findById(cabId);
		
		if(c.isPresent()) {
			Cab update = c.get();
			cDao.delete(update);
			return update;
		}
		
		else
			throw new DriverException("Cab Not Found By This Id :");
	}

	@Override
	public Driver viewDriverByCabId(Integer cabId) throws CabException {
		
		 Optional<Driver> c = dDao.findById(cabId);
		 
		 if(c.isPresent()) {
			 return c.get();
		 }
		 
		 else
			 throw new CabException("Cab Not Found By This Id :"+cabId);
		
	}
}
