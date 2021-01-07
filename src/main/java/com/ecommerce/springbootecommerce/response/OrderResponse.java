package com.ecommerce.springbootecommerce.response;

import com.ecommerce.springbootecommerce.model.Cart;

import java.util.List;

public class OrderResponse {
    private int orderId;
    private List<Cart> cartList;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "orderResp [orderId=" + orderId + ", cartList=" + cartList + "]";
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }
}
