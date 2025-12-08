package com.mtogo.service;

import com.mtogo.adapter.CustomerAdapter;
import com.mtogo.dto.CustomerDTO;
import com.mtogo.factory.CustomerFactory;
import com.mtogo.model.Customer;
import com.mtogo.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    // 🔥 Constructor Injection (REQUIRED for proper testing + Spring best
    // practices)
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
     * Creates a customer from a registration request with first and last names
     */
    public CustomerDTO createCustomerFromRequest(com.mtogo.dto.CreateCustomerRequestDTO request) {
        String fullName = request.getFullName();
        Customer customer = new Customer(
                fullName,
                request.getPassword(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getDateOfBirth());
        return CustomerAdapter.toDTO(customerRepository.save(customer));
    }

    /**
     * Creates a guest customer (no login, no account)
     */
    public CustomerDTO createGuestCustomer(String name, String password, String email, String phoneNumber,
            LocalDate dateOfBirth) {
        Customer customer = CustomerFactory.createGuestCustomer(name, password, email, phoneNumber, dateOfBirth);
        return CustomerAdapter.toDTO(customerRepository.save(customer));
    }

    /**
     * Authenticates a customer by email and password
     */
    public CustomerDTO authenticateCustomer(String email, String password) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        // Simple password comparison (consider using BCrypt in production)
        if (!customer.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        return CustomerAdapter.toDTO(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
