package com.ecommerce.springbootecommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id ;

    @Past
    private Date dateof_birth;

    @Size(min=3)
    private String job;
    @Size(min=3)
    private String name;
    @Email
    private String email;
    private Boolean isEnabled;
    @Size(min=4)
    private String username;
    @Size(min=6)
    private String password;
    private String userType;

}
