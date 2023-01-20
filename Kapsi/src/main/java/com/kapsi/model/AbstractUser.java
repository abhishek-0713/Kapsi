package com.kapsi.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractUser {

    private String userName;
    private String password;
    private String address;
    private String mobileNumber;
    private String email;


}
