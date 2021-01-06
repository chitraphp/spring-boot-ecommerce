package com.ecommerce.springbootecommerce.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@Entity
public class Cart {
    @Id@GeneratedValue
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy") private LocalDate dateAdded;
    @Email
    private String email;
    //private Integer order_id;
    private Double price;
    private Integer product_id;
    private String product_name;
    private Integer quantity;
//bufcart_id INTdate_added DATETIMEemail VARCHAR(255)order_id INTprice DOUBLEproduct_id INTproductname VARCHAR(255)quantity INT
}


