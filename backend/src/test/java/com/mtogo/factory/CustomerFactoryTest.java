package com.mtogo.factory;

import com.mtogo.model.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerFactoryTest {

    // ✅ EP: valid guest user
    @Test
    void createGuestCustomer_validInput_createsCustomer() {
        Customer customer = CustomerFactory.createGuestCustomer(
                "Guest",
                "12345",
                "guest@test.com",
                "City"
        );

        assertNotNull(customer);
        assertEquals("Guest", customer.getName());
        assertEquals("12345", customer.getPhone());
    }

    // ✅ BVA: shortest valid name (min length = 1)
    @Test
    void createGuestCustomer_minBoundaryName() {
        Customer customer = CustomerFactory.createGuestCustomer(
                "A",
                "12345",
                "a@test.com",
                "X"
        );
        assertEquals("A", customer.getName());
    }

    // ✅ EP: empty name is accepted (no validation in factory)
    @Test
    void createGuestCustomer_emptyName_accepted() {
        Customer customer = CustomerFactory.createGuestCustomer(
                "",
                "12345",
                "guest@test.com",
                "City"
        );
        assertEquals("", customer.getName());
    }

    // ✅ EP: null name is allowed (no validation in factory)
    @Test
    void createGuestCustomer_nullName_allowed() {
        Customer customer = CustomerFactory.createGuestCustomer(
                null, "12345", "guest@test.com", "City"
        );
        assertNull(customer.getName());
    }
}
