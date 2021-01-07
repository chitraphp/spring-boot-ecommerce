package com.ecommerce.springbootecommerce.exception;

import com.ecommerce.springbootecommerce.model.PlaceOrder;

public class PlaceOrderCustomException extends RuntimeException {

    public PlaceOrderCustomException(String message) {
        super(message);
    }

}
