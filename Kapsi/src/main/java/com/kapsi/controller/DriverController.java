package com.kapsi.controller;

import java.util.List;

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
	private DriverService dService;
	
	@PostMapping("/register")
	public ResponseEntity<Driver> registerDriverHandller(@Valid @RequestBody Driver driver) throws DriverException{
		
		Driver dri = dService.registerDriver(driver);
		
		return new ResponseEntity<>(dri,HttpStatus.CREATED);
		
	}
	
	
	@PutMapping("/update/{driverId}")
	public ResponseEntity<Driver> registerDriverHandller(@RequestParam String key, @PathVariable("driverId") Integer driverId, @RequestBody Driver driver) throws DriverException, LogInException{
		
		Driver dri = dService.updateDriver(key,driverId, driver);
		
		return new ResponseEntity<>(dri,HttpStatus.ACCEPTED);
		
	}
	
	
	@GetMapping("/getById/{driverId}")
	public ResponseEntity<Driver> getByIdDriverHandller(@RequestParam String key,@PathVariable("driverId") Integer driverId) throws DriverException, LogInException{
		
		Driver dri = dService.getDriverById(key,driverId);
		
		return new ResponseEntity<>(dri,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/getByName/{userName}")
	public ResponseEntity<Driver> getByNameDriverHandller(@RequestParam String key,@PathVariable("userName") String userName) throws DriverException, LogInException{
		
		Driver dri = dService.getDriverByName(key,userName);
		
		return new ResponseEntity<>(dri,HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/deleteById/{driverId}")
	public ResponseEntity<Driver> deleteByDriverIdHandller(@RequestParam String key,@PathVariable("driverId") Integer driverId) throws DriverException, LogInException{
		
		Driver dri = dService.deleteDriverById(key,driverId);
		
		return new ResponseEntity<>(dri,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/getAllDriver")
	public ResponseEntity<List<Driver>> getAllDriverHandller() throws DriverException{
		
		List<Driver> dri = dService.getAllDriver();
		
		return new ResponseEntity<>(dri,HttpStatus.ACCEPTED);
		
	}
	
     @PutMapping("/allocate")
    public ResponseEntity<Driver> allocateDriverHandller(@RequestParam Integer cabId, @RequestParam Integer driverId) throws DriverException, CabException{
    	 
    	  Driver d = dService.allocateCabToDriver(driverId, cabId);
    	 
    	  return new ResponseEntity<>(d,HttpStatus.ACCEPTED);
     }
	
      @GetMapping("/getCab")
     public ResponseEntity<Cab> getCabyDriverIdHandller(@RequestParam Integer driverId) throws DriverException{
    	 
    	 Cab c = dService.viewCabByDriverId(driverId);
    	 
    	 return new ResponseEntity<>(c,HttpStatus.CREATED);
    	 
     }
	
}
