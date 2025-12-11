package com.mtogo.service;

import com.mtogo.dto.CustomerDTO;
import com.mtogo.model.Customer;
import com.mtogo.repository.CustomerRepository;
import com.mtogo.adapter.CustomerAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService service;

    @Test
    void createCustomer_shouldSaveAndReturnDto() {
        // Arrange
        CustomerDTO dto = new CustomerDTO(null, "John", "strongpassword", "john@test.com", "22222",
                LocalDate.of(1990, 1, 1));
        Customer entity = CustomerAdapter.toEntity(dto);
        entity.setId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(entity);

        // Act
        CustomerDTO result = service.createCustomer(dto);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John", result.getName());
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void getCustomerById_whenFound_returnsDto() {
        Customer customer = new Customer("Alice", "strongpassword", "alice@test.com", "3333", LocalDate.of(1990, 1, 1));
        customer.setId(2L);

        when(customerRepository.findById(2L)).thenReturn(Optional.of(customer));

        CustomerDTO result = service.getCustomerById(2L);

        assertNotNull(result);
        assertEquals(2L, result.getId());
        assertEquals("Alice", result.getName());
    }

    @Test
    void getCustomerById_whenNotFound_returnsNull() {
        when(customerRepository.findById(99L)).thenReturn(Optional.empty());

        CustomerDTO result = service.getCustomerById(99L);

        assertNull(result);
    }

    @Test
    void getAllCustomers_whenEmpty_returnsEmptyList() {
        when(customerRepository.findAll()).thenReturn(List.of());

        List<CustomerDTO> result = service.getAllCustomers();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
