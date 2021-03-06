package com.ecommerce.springbootecommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import java.util.Date;

@Entity
public class PlaceOrder {
    @Id
    @GeneratedValue
    private int orderId;
    @Email
    private String email;
    private Date order_date;
    private String order_status;
    private double total_price;

    public PlaceOrder(Integer order_id, @Email String email, Date order_date, String order_status, double total_price) {
        this.orderId = order_id;
        this.email = email;
        this.order_date = order_date;
        this.order_status = order_status;
        this.total_price = total_price;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public PlaceOrder() {
        super();
    }

//order_id INTemail VARCHAR(255)order_date DATETIMEorder_status VARCHAR(255)total_cost DOUBLE
}
