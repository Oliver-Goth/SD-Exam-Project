package com.mtogo.factory;

import java.time.LocalDate;

import com.mtogo.model.Customer;

public class CustomerFactory {

    public static Customer createGuestCustomer(String name, String password, String phoneNumber, String email,
            LocalDate dateOfBirth) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setPassword(password);
        customer.setEmail(email);
        customer.setPhoneNumber(phoneNumber);
        customer.setDateOfBirth(dateOfBirth);
        return customer;
    }
}
