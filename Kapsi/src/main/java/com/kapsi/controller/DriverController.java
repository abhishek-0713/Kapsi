package com.kapsi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kapsi.exceptions.CabException;
import com.kapsi.exceptions.DriverException;
import com.kapsi.exceptions.LogInException;
import com.kapsi.model.Cab;
import com.kapsi.model.Driver;
import com.kapsi.service.DriverService;

@RestController
@RequestMapping("/driver")
public class DriverController {

	
	@Autowired
	private DriverService driverService;


	/************************************************  Driver Services  ********************************************************/


	/*--------------------------------------------   Add Driver Account  ------------------------------------------------*/
	@PostMapping("/create")
	public ResponseEntity<Driver> registerDriverHandler(@Valid @RequestBody Driver driver) throws DriverException{
		
		Driver registerDriver = driverService.registerDriver(driver);
		return new ResponseEntity<>(registerDriver, HttpStatus.CREATED);
		
	}


	/*--------------------------------------------   Update Driver Account  ------------------------------------------------*/
	@PutMapping("/update/{driverId}")
	public ResponseEntity<Driver> updateDriverHandler(@RequestParam String key, @PathVariable("driverId") Integer driverId, @RequestBody Driver driver) throws DriverException, LogInException{
		
		Driver updateDriver = driverService.updateDriver(key,driverId, driver);
		return new ResponseEntity<>(updateDriver, HttpStatus.ACCEPTED);
		
	}


	/*--------------------------------------------   Delete Driver Account  ------------------------------------------------*/
	@DeleteMapping("/delete/{driverId}")
	public ResponseEntity<Driver> deleteByDriverIdHandler(@RequestParam String key, @PathVariable("driverId") Integer driverId) throws DriverException, LogInException{
		
		Driver deleteDriver = driverService.deleteDriverById(key,driverId);
		return new ResponseEntity<>(deleteDriver, HttpStatus.OK);
		
	}



	/************************************************  Cab Services  ********************************************************/


	/*--------------------------------------------   Allocate Driver TO Cab  ------------------------------------------------*/
	@PutMapping("/allocate/cab")
	public ResponseEntity<Driver> allocateDriverHandler(@RequestParam String key, @RequestParam Integer cabId, @RequestParam Integer driverId) throws DriverException, CabException, LogInException {
    	 
    	  Driver allocateCabToDriver = driverService.allocateCabToDriver(key, driverId, cabId);
    	  return new ResponseEntity<>(allocateCabToDriver, HttpStatus.ACCEPTED);
	}


	/*--------------------------------------------   Get Cab Details   ------------------------------------------------*/
	@GetMapping("/cab")
	public ResponseEntity<Cab> getCabByDriver(@RequestParam String key, @RequestParam Integer cabId) throws DriverException, LogInException {
    	 
    	 Cab cab = driverService.viewCabByDriverId(key, cabId);
    	 return new ResponseEntity<>(cab, HttpStatus.OK);
    	 
	}
	
}
