package com.kapsi.model;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractUser {

    @Column(unique = true)
    private String userName;
  
    @NotNull
	@Size(min = 8,message="password should be minimum 8 characters")
    private String password;
    
    @NotNull
    private String address;

    
  	@NotNull
  	@Column(unique = true)
  	@Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Enter valid 10 digit mobile number")
    private String mobileNumber;

	@NotNull
	@Email
    private String email;

}
