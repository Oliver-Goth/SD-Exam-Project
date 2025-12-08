package com.mtogo.controller;

import com.mtogo.dto.CreateCustomerRequestDTO;
import com.mtogo.dto.CustomerDTO;
import com.mtogo.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
// Allow local frontend dev servers (Vite default 5173 and CRA 3000). Change for
// production.
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:5173" })
@Tag(name = "Customers", description = "Customer management, profile handling, and guest account registration")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // ------------------------------------------------------
    // GET /api/customers (List all customers)
    // ------------------------------------------------------
    @GetMapping
    @Operation(summary = "Get all customers", description = "Returns a list of all registered customers in the system.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of customers", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerDTO.class)))
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // ------------------------------------------------------
    // GET /api/customers/{id} (Get single customer)
    // ------------------------------------------------------
    @GetMapping("/{id}")
    @Operation(summary = "Get customer by ID", description = "Retrieves detailed information about a customer using their unique ID.")
    @ApiResponse(responseCode = "200", description = "Customer found and returned")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    public CustomerDTO getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    // ------------------------------------------------------
    // POST /api/customers (Create full customer)
    // ------------------------------------------------------
    @PostMapping
    @Operation(summary = "Create a new customer", description = "Registers a full customer account including profile information. Combines first and last names into a single name field.")
    @ApiResponse(responseCode = "201", description = "Customer created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid customer data supplied")
    public CustomerDTO createCustomer(@RequestBody CreateCustomerRequestDTO request) {
        return customerService.createCustomerFromRequest(request);
    }

    // Legacy endpoint for backward compatibility
    // POST /api/customers/legacy
    // Using DTO with pre-combined name
    @PostMapping("/legacy")
    @Operation(summary = "Create a new customer (legacy)", description = "Legacy endpoint for creating a customer with combined name. Use the main POST endpoint instead.")
    @ApiResponse(responseCode = "201", description = "Customer created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid customer data supplied")
    public CustomerDTO createCustomerLegacy(@RequestBody CustomerDTO dto) {
        return customerService.createCustomer(dto);
    }

    // ------------------------------------------------------
    // POST /api/customers/guest (Guest customer)
    // ------------------------------------------------------
    @PostMapping("/guest")
    @Operation(summary = "Create guest customer", description = "Creates a temporary guest customer record for users without an account.")
    @ApiResponse(responseCode = "201", description = "Guest customer created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid or missing guest data")
    public CustomerDTO createGuestCustomer(@RequestBody Map<String, String> body) {
        String dateOfBirthStr = body.get("dateOfBirth");
        LocalDate dateOfBirth = dateOfBirthStr != null ? LocalDate.parse(dateOfBirthStr) : null;
        return customerService.createGuestCustomer(
                body.get("name"),
                body.get("password"),
                body.get("email"),
                body.get("phoneNumber"),
                dateOfBirth);
    }

    // ------------------------------------------------------
    // POST /api/customers/login (Customer login)
    // ------------------------------------------------------
    @PostMapping("/login")
    @Operation(summary = "Customer login", description = "Authenticates a customer using email and password.")
    @ApiResponse(responseCode = "200", description = "Login successful, returns customer details")
    @ApiResponse(responseCode = "401", description = "Invalid email or password")
    public CustomerDTO loginCustomer(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");
        return customerService.authenticateCustomer(email, password);
    }

    // ------------------------------------------------------
    // DELETE /api/customers/{id}
    // ------------------------------------------------------
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a customer", description = "Removes a customer from the system using their ID.")
    @ApiResponse(responseCode = "204", description = "Customer deleted successfully")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
