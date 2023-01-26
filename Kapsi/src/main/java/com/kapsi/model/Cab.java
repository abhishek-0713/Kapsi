package com.kapsi.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
   
    @NotNull
    private String carType;
    
    @Min(6)
    @Max(21)
    private Float perKmRate;
    
    @NotNull
    private boolean avalibilityStatus;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id")
    private Driver driver;


    public Cab(String carType, Float perKmRate) {
        this.carType = carType;
        this.perKmRate = perKmRate;
   //     this.availabilityStatus = availabilityStatus;
    }
}
