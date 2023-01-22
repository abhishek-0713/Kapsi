package com.kapsi.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
    private Integer cabId;
   
    @NotNull
    private String carType;
    
    @Min(6)
    @Max(21)
    private Float perKmRate;
    
    @NotNull
    private boolean avalibilityStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driverId")
    @JsonIgnore
    private Driver driver;


}
