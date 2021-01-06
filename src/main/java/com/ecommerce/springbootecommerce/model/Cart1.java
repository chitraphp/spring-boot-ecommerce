package com.ecommerce.springbootecommerce.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Cart1 {
    @Id
    @GeneratedValue
    private Integer Id;
    @Email
    private String email;
    @OneToMany(mappedBy = "cart1")
    private List<CartItem> Items;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CartItem> getItems() {
        return Items;
    }

    public void setItems(List<CartItem> items) {
        Items = items;
    }

    public Cart1(String email){
        this.email = email;
        Items = new ArrayList<CartItem>();
    }
}
