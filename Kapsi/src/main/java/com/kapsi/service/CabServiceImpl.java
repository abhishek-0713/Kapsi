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
	private CabRepo cabRepo;
	
	@Autowired
	private DriverRepo driverRepo;
	
	@Autowired
	private CurrentSessionRepo currentSessionRepo;


	/*-------------------------------- Add Cab Implementation ---------------------------------*/
	@Override
	public Cab registerCab(Cab cab) throws DriverException {
		
		Cab registerCab = cabRepo.save(cab);
		return registerCab;
	}


	/*-------------------------------- Update Admin Implementation ---------------------------------*/
	@Override
	public Cab updateCab(String key,Integer cabId, Cab cab) throws DriverException, LogInException {

		CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
		if(currentUserSession == null) {
			throw new LogInException("No User LoggedIn");
		}
		
		Optional<Cab> cabOptional = cabRepo.findById(cabId);
		if(cabOptional.isPresent()) {
			Cab updateCab =  cabOptional.get();
			cabRepo.save(updateCab);
			return updateCab;
		}
		throw new DriverException("Cab Not Found By This Id :" + cabId);
		
	}


	/*-------------------------------- Get All Cabs Implementation ---------------------------------*/
	@Override
	public List<Cab> getAllCabs(String key) throws DriverException, LogInException {

		CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
		if(currentUserSession == null) {
			throw new LogInException("No User LoggedIn");
		}

		List<Cab> allCabs = cabRepo.findAll();
		if(allCabs.isEmpty()) {
			throw new DriverException("Cabs Data Not Found :");
		}
		
		return allCabs;
	}


	/*-------------------------------- Delete Cab Implementation ---------------------------------*/
	@Override
	public Cab deleteCab(String key,Integer cabId) throws DriverException, LogInException {

		CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
		if(currentUserSession == null) {
			throw new LogInException("No User LoggedIn");
		}
		
		Optional<Cab> cabOptional = cabRepo.findById(cabId);
		
		if(cabOptional.isPresent()) {
			Cab update = cabOptional.get();
			cabRepo.delete(update);
			return update;
		}
		throw new DriverException("Cab Not Found By This Id :");
	}


	/*-------------------------------- View Cab Implementation ---------------------------------*/
	@Override
	public Driver viewDriverByCabId(String key, Integer cabId) throws CabException, LogInException {

		CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
		if(currentUserSession == null) {
			throw new LogInException("No User LoggedIn");
		}

		 Optional<Driver> optionalDriver = driverRepo.findById(cabId);
		 if(optionalDriver.isPresent()) {
			 return optionalDriver.get();
		 }
		 throw new CabException("Cab Not Found By This Id :"+cabId);
		
	}


}
