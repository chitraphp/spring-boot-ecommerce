package com.ecommerce.springbootecommerce.model;

import javax.persistence.*;

@Entity
public class Authority {
    @Id
    @GeneratedValue
    private Integer id;
    private String authority;
    private String username;





    //AUTHORITY_ID INTauthority VARCHAR(255)username VARCHAR(255)user_id INT
}
