package com.kapsi.service;

import java.util.List;

import com.kapsi.exceptions.CabException;
import com.kapsi.exceptions.DriverException;
import com.kapsi.exceptions.LogInException;
import com.kapsi.model.Cab;
import com.kapsi.model.Driver;

public interface CabService {

	public Cab registerCab(Cab cab)throws DriverException;
	
	public Cab updateCab(String Key,Integer cabId,Cab cab)throws DriverException,LogInException;
	
	public List<Cab> getAllCabs()throws DriverException;
	
	public Cab deleteCab(String Key,Integer cabId)throws DriverException,LogInException;
	
    public Driver viewDriverByCabId(Integer cabId)throws CabException;
	
}
