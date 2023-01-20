package com.kapsi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends AbstractUser{
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Integer Id;
	
//	@NotNull
//	@Size(min = 3, message = "Invalid  name [ contains at least 3 characters ]")
//	private String Name;
//
//	@NotNull
//	@Size(min = 10, max = 10 ,message = "Invalid Mobile Number [ 10 Digit Only ] ")
//	private String mobileNumber;
//
//	@NotNull
//	@Size(min = 6, max = 12, message = "Invalid Password [ must be 6 to 8 characters ]")
//	private String password;

	
}
