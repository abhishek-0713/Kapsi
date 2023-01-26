package com.kapsi.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver extends AbstractUser{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private Integer driverId;
    
    @NotNull
	@Pattern(regexp = "[A-Z||a-z]{2}[0-9]{5}", message = "Enter valid License number")
    private String licenceNo;
    
    @Max(value=5)
   	@Min(value=1)
    private Float rating;


    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cab_id" )
    private Cab cab;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "driver")
    List<TripBooking> tripBookings = new ArrayList<>();


    public Driver(String userName, @NotNull @Size(min = 8, message = "password should be minimum 8 characters") String password, @NotNull String address, @NotNull @Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Enter valid 10 digit mobile number") String mobileNumber, @NotNull @Email String email, String licenceNo, Float rating) {
        super(userName, password, address, mobileNumber, email);
        this.licenceNo = licenceNo;
        this.rating = rating;
    }
}
