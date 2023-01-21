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
import com.kapsi.model.Cab;
import com.kapsi.service.CabService;

@RestController
@RequestMapping("/cab")
public class CabController {

    @Autowired
	private CabService cService;
		
		
	@PostMapping("/register")
	public ResponseEntity<Cab> registerCab(@RequestBody Cab cab) throws DriverException{
		
		Cab c = cService.registerCab(cab);
		
		return new ResponseEntity<>(c,HttpStatus.CREATED); 
		
	}
	
	@PutMapping("/update/{cabId}")
	public ResponseEntity<Cab> updateCabHandller(@PathVariable("cabId") Integer cabId, @RequestBody Cab cab) throws DriverException{
		
		Cab cc = cService.updateCab(cabId, cab);
		
		return new ResponseEntity<>(cc,HttpStatus.CREATED); 
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Cab>> getAllCabHandller() throws DriverException{
		
		List<Cab> c = cService.getAllCabs();
		
		return new ResponseEntity<>(c,HttpStatus.ACCEPTED); 
		
	}
	
	@DeleteMapping("/delete/{cabId}")
	public ResponseEntity<Cab> deleteCabHandller(@PathVariable("cabId") Integer cabId) throws DriverException{
		
		Cab cc = cService.deleteCab(cabId);
		
		return new ResponseEntity<>(cc,HttpStatus.OK); 
		
	}
	
	
	
//	@GetMapping("/cab/{status}")
//	public ResponseEntity<List<Cab>> getAvailableCabsHandler(@Valid @PathVariable("status") Boolean status){
//		
//		List<Cab> clist = cService.findByAvalibityStatus(status);
//		
//		return new ResponseEntity<List<Cab>>(clist,HttpStatus.OK);
//	}
	
}
