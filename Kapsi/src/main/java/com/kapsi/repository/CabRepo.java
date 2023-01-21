package com.kapsi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kapsi.model.Cab;

@Repository
public interface CabRepo extends JpaRepository<Cab, Integer> {

//	public List<Cab> findByAvailbilityStatus(Boolean avalibilityStatus);

}
