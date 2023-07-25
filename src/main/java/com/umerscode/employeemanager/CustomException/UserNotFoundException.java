package com.umerscode.employeemanager.CustomException;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String s) {
        super(s);
    }
}
