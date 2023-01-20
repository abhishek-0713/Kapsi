package com.kapsi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends AbstractUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer CustomerId;

	
}
