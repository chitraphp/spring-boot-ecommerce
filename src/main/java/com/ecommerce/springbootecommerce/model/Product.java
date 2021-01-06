package com.ecommerce.springbootecommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.awt.image.BufferedImage;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Integer id;

    private String description;
    private double price;
    //private BufferedImage image;
    //private LongBlob image;
    //private String imageUrl;
    @Lob
    private byte[] productimage;

    @NotNull(message = "Product name is required.")
    private String name;

    private Integer quantity;

    public Product(){
        super();
    }

    public Product(Integer id,String description, double price, byte[] image, @NotNull(message = "Product name is required.") String name, Integer quantity) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.productimage = image;
        this.name = name;
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public byte[] getProductimage() {
        return productimage;
    }

    public void setProductimage(byte[] productimage) {
        this.productimage = productimage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}


