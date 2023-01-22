package com.kapsi.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	private LocalDate fromDateTime;
	private LocalDate toDateTime;
	private boolean status;
	private float distanceInKm;
	private float bill;


	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "driverId")
	private Driver driver;
	

}