package com.ecommerce.springbootecommerce.exception;

public class AddressCustomException extends RuntimeException {
    public AddressCustomException(String message) {
        super(message);
    }
}
