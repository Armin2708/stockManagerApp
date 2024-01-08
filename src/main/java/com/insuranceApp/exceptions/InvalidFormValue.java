package com.insuranceApp.exceptions;

public class InvalidFormValue extends RuntimeException {
    public InvalidFormValue(String message) {
        super(message);
    }
}
