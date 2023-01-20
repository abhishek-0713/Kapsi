package com.kapsi.model;

import javax.persistence.*;

@Entity
public class Driver extends AbstractUser{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer driverId;
    private Integer licenceNo;
    private Float rating;

    @OneToOne
    private Cab cab;

}
