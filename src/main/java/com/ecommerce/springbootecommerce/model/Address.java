package com.ecommerce.springbootecommerce.model;

import javax.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue
    private Integer id;
    private String address;
    private String city;
    private String state;
    private String country;
    private Integer zipcode;
    private String phoneNumber;
    @OneToOne(mappedBy = "Address")
    @JoinColumn(name="user_id")
    private User user;


}
