package com.kapsi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cab {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cabId;
    private String carType;
    private Float perKmRate;
    private boolean avalibilityStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driverId")
    @JsonIgnore
    private Driver driver;

//    public Cab(String carType, Float perKmRate, boolean avalibityStatus) {
//        this.carType = carType;
//        this.perKmRate = perKmRate;
//        this.avalibilityStatus = avalibityStatus;
//    }

}
