package com.mtogo.service;

import com.mtogo.adapter.CustomerAdapter;
import com.mtogo.dto.CustomerDTO;
import com.mtogo.factory.CustomerFactory;
import com.mtogo.model.Customer;
import com.mtogo.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    // 🔥 Constructor Injection (REQUIRED for proper testing + Spring best practices)
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerAdapter::toDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(CustomerAdapter::toDTO)
                .orElse(null);
    }

    public CustomerDTO createCustomer(CustomerDTO dto) {
        Customer customer = CustomerAdapter.toEntity(dto);
        return CustomerAdapter.toDTO(customerRepository.save(customer));
    }

    /**
     * Creates a guest customer (no login, no account)
     */
    public CustomerDTO createGuestCustomer(String name, String phone, String email, String address) {
        Customer customer = CustomerFactory.createGuestCustomer(name, phone, email, address);
        return CustomerAdapter.toDTO(customerRepository.save(customer));
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
