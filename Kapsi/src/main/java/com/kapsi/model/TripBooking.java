package com.kapsi.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripBooking {
	@Id
	private Integer tripBookingId;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "CustomerId")
	@NotNull
	private Customer customer;
//	@OneToOne
//	private Driver driver;
	@NotNull
	private String fromLocation;
	@NotNull
	private String toLocation;
	@NotNull
	private LocalDateTime fromDateTime;
	@NotNull
	private LocalDateTime toDateTime;
	private boolean status;
	private float diatanceInKm;
	private float bill;
	

}