package com.kapsi.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.kapsi.exceptions.DriverException;
import com.kapsi.model.Driver;
import com.kapsi.service.DriverService;

@RestController
@RequestMapping("/driver")
public class DriverController {

	
	@Autowired
	private DriverService dService;
	
	@PostMapping("/register")
	public ResponseEntity<Driver> registerDriverHandller(@RequestBody Driver driver) throws DriverException{
		
		Driver dri = dService.registerDriver(driver);
		
		return new ResponseEntity<>(dri,HttpStatus.CREATED);
		
	}
	
	
	@PutMapping("/update/{driverId}")
	public ResponseEntity<Driver> registerDriverHandller(@PathVariable("driverId") Integer driverId, @RequestBody Driver driver) throws DriverException{
		
		Driver dri = dService.updateDriver(driverId, driver);
		
		return new ResponseEntity<>(dri,HttpStatus.ACCEPTED);
		
	}
	
	
	@GetMapping("/getById/{driverId}")
	public ResponseEntity<Driver> getByIdDriverHandller(@PathVariable("driverId") Integer driverId) throws DriverException{
		
		Driver dri = dService.getDriverById(driverId);
		
		return new ResponseEntity<>(dri,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/getByName/{userName}")
	public ResponseEntity<Driver> getByNameDriverHandller(@PathVariable("userName") String userName) throws DriverException{
		
		Driver dri = dService.getDriverByName(userName);
		
		return new ResponseEntity<>(dri,HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/deleteById/{driverId}")
	public ResponseEntity<Driver> deleteByDriverIdHandller(@PathVariable("driverId") Integer driverId) throws DriverException{
		
		Driver dri = dService.deleteDriverById(driverId);
		
		return new ResponseEntity<>(dri,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/getAllDriver")
	public ResponseEntity<List<Driver>> getAllDriverHandller() throws DriverException{
		
		List<Driver> dri = dService.getAllDriver();
		
		return new ResponseEntity<>(dri,HttpStatus.ACCEPTED);
		
	}
	
	
//	@GetMapping("/getByCarType/{carType}")
//	public ResponseEntity<Driver> getDriverByCarTypeHandller(@PathVariable("carType") String carType) throws DriverException{
//		
//		Driver dri = dService.getDriverByCab(carType);
//		
//		return new ResponseEntity<>(dri,HttpStatus.ACCEPTED);
//		
//	}
	
}
