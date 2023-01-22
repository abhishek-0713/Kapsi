package com.kapsi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private Integer licenceNo;
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
