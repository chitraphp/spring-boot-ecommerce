package com.ecommerce.springbootecommerce.exception;

import com.ecommerce.springbootecommerce.model.Product;

public class ProductCustomException extends RuntimeException {

    public ProductCustomException(String message) {
        super(message);
    }

}
