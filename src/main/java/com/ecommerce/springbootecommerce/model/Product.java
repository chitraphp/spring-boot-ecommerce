package com.ecommerce.springbootecommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    private String imageUrl;

    @NotNull(message = "Product name is required.")
    private String name;

    private Integer quantity;

    public Product(String description, double price, String imageUrl, @NotNull(message = "Product name is required.") String name, Integer quantity) {
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.name = name;
        this.quantity = quantity;
    }
}


