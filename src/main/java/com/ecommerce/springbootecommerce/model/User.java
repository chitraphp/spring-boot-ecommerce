package com.ecommerce.springbootecommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
    private String userId;
    private Integer age;
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
    private String userCol;

    public User(@Past Date dateof_birth, @Size(min = 3) String job, String userId, Integer age, @Size(min = 3) String name, @Email String email, Boolean isEnabled, @Size(min = 4) String username, @Size(min = 6) String password, String userType) {
        this.dateof_birth = dateof_birth;
        this.job = job;
        this.userId = userId;
        this.age = age;
        this.name = name;
        this.email = email;
        this.isEnabled = isEnabled;
        this.username = username;
        this.password = password;
        this.userType = userType;
        //this.userCol = userCol;
    }
}
