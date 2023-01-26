package com.kapsi.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
    @JsonIgnore
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


    public Driver(String userName, String password, String address, String mobileNumber, String email, Integer licenceNo, Float rating) {
        super(userName, password, address, mobileNumber, email);
        this.licenceNo = licenceNo;
        this.rating = rating;
    }

}
