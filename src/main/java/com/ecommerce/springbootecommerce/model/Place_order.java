package com.ecommerce.springbootecommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Place_order {
    @Id
    @GeneratedValue
    private Integer id;
    private Date order_date;
    private String order_status;
    private double total_price;


    //order_id INTemail VARCHAR(255)order_date DATETIMEorder_status VARCHAR(255)total_cost DOUBLE
}