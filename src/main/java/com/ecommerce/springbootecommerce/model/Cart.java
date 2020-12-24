package com.ecommerce.springbootecommerce.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Cart {
    @Id@GeneratedValue
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy") private LocalDate dateCreated;



//bufcart_id INTdate_added DATETIMEemail VARCHAR(255)order_id INTprice DOUBLEproduct_id INTproductname VARCHAR(255)quantity INT
}


