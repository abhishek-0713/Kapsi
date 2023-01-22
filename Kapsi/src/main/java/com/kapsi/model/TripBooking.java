package com.kapsi.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripBooking {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer tripBookingId;
	private String fromLocation;
	private String toLocation;
	private LocalDateTime fromDateTime;
	private LocalDateTime toDateTime;
	
	
	private boolean status;
	private float distanceInKm;
	private float bill;

    @JsonIgnore
	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer;
    @JsonIgnore
	@ManyToOne
	@JoinColumn(name = "driverId")
	private Driver driver;
    
	public boolean getStatus() {
		// TODO Auto-generated method stub
		return status;
	}
    
	

}