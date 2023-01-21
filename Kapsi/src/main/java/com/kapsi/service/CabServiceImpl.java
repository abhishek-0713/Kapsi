package com.kapsi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapsi.exceptions.DriverException;
import com.kapsi.model.Cab;
import com.kapsi.repository.CabRepo;

@Service
public class CabServiceImpl implements CabService {

	@Autowired
	private CabRepo cDao;
	
//	@Override
//	public List<Cab> findByAvalibityStatus(Boolean avalibiltyStatus) {
//		
//		List<Cab> list = new ArrayList<>();
//	
//		if(avalibiltyStatus) {
//			list = cDao.findByAvailbilityStatus(true);
//		}
//		else {
//			list  = cDao.findByAvailbilityStatus(false);
//		}
//		
//		return list;
//	}

	@Override
	public Cab registerCab(Cab cab) throws DriverException {
		
		Cab c = cDao.save(cab);
		
		return c;
	}

	@Override
	public Cab updateCab(Integer cabId, Cab cab) throws DriverException {
		
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
	public Cab deleteCab(Integer cabId) throws DriverException {
		
		Optional<Cab> c = cDao.findById(cabId);
		
		if(c.isPresent()) {
			Cab update = c.get();
			cDao.delete(update);
			return update;
		}
		
		else
			throw new DriverException("Cab Not Found By This Id :");
	}
}
