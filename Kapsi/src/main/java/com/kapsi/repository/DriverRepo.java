package com.kapsi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kapsi.model.Driver;

@Repository
public interface DriverRepo extends JpaRepository<Driver, Integer> {

	@Query("select d from Driver d where d.userName=?1")
	public Optional<Driver> getByName(String userName); 
	
//	@Query("select d from Driver d where d.Cab.carType=?1")
//	public List<Driver> getDriverByCab(String carType);

}
