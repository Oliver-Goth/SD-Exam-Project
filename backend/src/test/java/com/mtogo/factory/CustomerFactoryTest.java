package com.mtogo.factory;

import com.mtogo.model.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

class CustomerFactoryTest {

    // ✅ EP: valid guest user
    @Test
    void createGuestCustomer_validInput_createsCustomer() {
        Customer customer = CustomerFactory.createGuestCustomer(
                "Guest",
                "strongpassword",
                "12345",
                "guest@test.com",
                LocalDate.of(1990, 1, 1));

        assertNotNull(customer);
        assertEquals("Guest", customer.getName());
        assertEquals("12345", customer.getPhoneNumber());
    }

    // ✅ BVA: shortest valid name (min length = 1)
    @Test
    void createGuestCustomer_minBoundaryName() {
        Customer customer = CustomerFactory.createGuestCustomer(
                "A",
                "strongpassword",
                "12345",
                "guest@test.com",
                LocalDate.of(1990, 1, 1));
        assertEquals("A", customer.getName());
    }

    // ✅ EP: empty name is accepted (no validation in factory)
    @Test
    void createGuestCustomer_emptyName_accepted() {
        Customer customer = CustomerFactory.createGuestCustomer(
                "",
                "strongpassword",
                "12345",
                "guest@test.com",
                LocalDate.of(1990, 1, 1));
        assertEquals("", customer.getName());
    }

    // ✅ EP: null name is allowed (no validation in factory)
    @Test
    void createGuestCustomer_nullName_allowed() {
        Customer customer = CustomerFactory.createGuestCustomer(
                null, "strongpassword", "12345", "guest@test.com", LocalDate.of(1990, 1, 1));
        assertNull(customer.getName());
    }
}
