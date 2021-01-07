package com.ecommerce.springbootecommerce.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Cart implements Serializable {
    private static final long serialVersionUID = 4049687597028261161L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private int cartId;

    @Column(name = "order_id", nullable = true)
    private int orderId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "cart [cartId=" + cartId + ", orderId=" + orderId + ", email=" + email + ", dateAdded="
                + dateAdded + ", quantity=" + quantity + ", price=" + price + ", productId=" + productId
                + ", productname=" + productname + "]";
    }

    private String email;

    @Column(name = "date_added")
    private Date dateAdded;

    private int quantity;
    private double price;
    @Column(name = "product_id")
    private int productId;

    private String productname;

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getcartId() {
        return cartId;
    }

    public void setcartId(int cartId) {
        this.cartId = cartId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}


