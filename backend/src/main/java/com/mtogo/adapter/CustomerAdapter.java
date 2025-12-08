package com.mtogo.adapter;

import com.mtogo.dto.CustomerDTO;
import com.mtogo.model.Customer;

public class CustomerAdapter {

    public static CustomerDTO toDTO(Customer customer) {
        if (customer == null)
            return null;

        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getPassword(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getDateOfBirth());
    }

    public static Customer toEntity(CustomerDTO dto) {
        if (dto == null)
            return null;

        Customer customer = new Customer();

        // DO NOT SET ID for new customers (important)
        if (dto.getId() != null) {
            customer.setId(dto.getId());
        }

        customer.setName(dto.getName());
        customer.setPassword(dto.getPassword());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setEmail(dto.getEmail());
        customer.setDateOfBirth(dto.getDateOfBirth());

        return customer;
    }
}
