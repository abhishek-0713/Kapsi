package com.kapsi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Driver extends AbstractUser{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer driverId;
    private Integer licenceNo;
    private Float rating;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cabId" )
    private Cab cab;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "driver")
    List<TripBooking> tripBookings = new ArrayList<>();

}
