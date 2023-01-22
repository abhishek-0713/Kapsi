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
    @JsonIgnore
    @Column(name = "cab_id")
    private Integer cabId;
    private String carType;
    private Float perKmRate;
//    private boolean availabilityStatus;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id")
    private Driver driver;


    public Cab(String carType, Float perKmRate) {
        this.carType = carType;
        this.perKmRate = perKmRate;
//        this.availabilityStatus = availabilityStatus;
    }
}
