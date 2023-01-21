package com.kapsi.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractUser {

    @Column(unique = true)
    private String userName;
    private String password;
    private String address;

    @Column(unique = true)
    private String mobileNumber;
    private String email;


}
