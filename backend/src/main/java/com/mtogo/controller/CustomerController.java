package com.mtogo.controller;

import com.mtogo.dto.CustomerDTO;
import com.mtogo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public CustomerDTO createCustomer(@RequestBody CustomerDTO dto) {
        return customerService.createCustomer(dto);
    }

    /**
     * Guest customer endpoint — used when no account exists
     */
    @PostMapping("/guest")
    public CustomerDTO createGuestCustomer(@RequestBody Map<String, String> body) {
        return customerService.createGuestCustomer(
                body.get("name"),
                body.get("phone"),
                body.get("email"),
                body.get("address")
        );
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
