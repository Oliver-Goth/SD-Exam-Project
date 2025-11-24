package com.mtogo.adapter;

import com.mtogo.dto.CustomerDTO;
import com.mtogo.model.Customer;

public class CustomerAdapter {

    public static CustomerDTO toDTO(Customer customer) {
        if (customer == null) return null;

        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress()
        );
    }

    public static Customer toEntity(CustomerDTO dto) {
        if (dto == null) return null;

        Customer customer = new Customer();

        // DO NOT SET ID for new customers (important)
        if (dto.getId() != null) {
            customer.setId(dto.getId());
        }

        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());

        return customer;
    }
}
