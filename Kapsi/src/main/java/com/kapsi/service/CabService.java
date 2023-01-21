package com.kapsi.service;

import java.util.List;

import com.kapsi.exceptions.DriverException;
import com.kapsi.model.Cab;

public interface CabService {

	public Cab registerCab(Cab cab)throws DriverException;
	
	public Cab updateCab(Integer cabId,Cab cab)throws DriverException;
	
	public List<Cab> getAllCabs()throws DriverException;
	
	public Cab deleteCab(Integer cabId)throws DriverException;
	
//	public List<Cab> findByAvalibityStatus(Boolean avalibiltyStatus);
	
}
