package com.mtogo.controller;

import com.mtogo.dto.CustomerDTO;
import com.mtogo.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerService customerService;

    @Test
    @WithMockUser
    void getAllCustomers_returnsList() throws Exception {
        Mockito.when(customerService.getAllCustomers())
                .thenReturn(List.of(new CustomerDTO(1L, "Test", "a@b.com", "123", "City")));

        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    @WithMockUser
    void getCustomerById_found_returnsCustomer() throws Exception {
        Mockito.when(customerService.getCustomerById(1L))
                .thenReturn(new CustomerDTO(1L, "Test", "a@b.com", "123", "City"));

        mockMvc.perform(get("/api/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test"));
    }

    @Test
    @WithMockUser
    void getCustomerById_notFound_returns200WithNull() throws Exception {
        Mockito.when(customerService.getCustomerById(999L)).thenReturn(null);

        mockMvc.perform(get("/api/customers/999"))
                .andExpect(status().isOk());
    }
}
