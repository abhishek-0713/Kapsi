package com.kapsi.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends AbstractUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
	List<TripBooking> tripBookings = new ArrayList<>();
}
