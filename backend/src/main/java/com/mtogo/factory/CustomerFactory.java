package com.mtogo.factory;

import com.mtogo.model.Customer;

public class CustomerFactory {

    public static Customer createGuestCustomer(String name, String phone, String email, String address) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setPhone(phone);
        customer.setEmail(email);
        customer.setAddress(address);
        return customer;
    }
}
