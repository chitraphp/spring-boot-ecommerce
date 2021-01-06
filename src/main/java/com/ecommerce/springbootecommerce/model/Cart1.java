package com.ecommerce.springbootecommerce.model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Cart1 {
    @Id
    @GeneratedValue
    private Integer Id;

    private String userId;
    @OneToMany(mappedBy = "cart1")
    private List<CartItem> Items;
    public String getuserId() {
        return userId;
    }

    public void setuserId(String userId) {
        this.userId = userId;
    }

    public List<CartItem> getItems() {
        return Items;
    }

    public void setItems(List<CartItem> items) {
        Items = items;
    }

    public Cart1(String userId){
        this.userId = userId;
        Items = new ArrayList<CartItem>();
    }
}
