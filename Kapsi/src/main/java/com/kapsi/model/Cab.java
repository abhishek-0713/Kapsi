package com.kapsi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driverId")
    private Driver driver;

    public Cab(String carType, Float perKmRate) {
        this.carType = carType;
        this.perKmRate = perKmRate;
    }

}
