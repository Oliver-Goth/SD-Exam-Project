package com.mtogo.integration;

import com.mtogo.controller.OrderController;
import com.mtogo.service.OrderService;
import com.mtogo.dto.OrderDTO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@AutoConfigureMockMvc(addFilters = false)   // Disable Spring Security
@ActiveProfiles("test")
class OrderIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void getAllOrders_returns200() throws Exception {

        // Create mock OrderDTO
        OrderDTO dto = new OrderDTO();
        dto.setId(1L);
        dto.setStatus("CONFIRMED");
        dto.setCreatedAt(LocalDateTime.now());
        dto.setTotal(BigDecimal.valueOf(120.0));

        // Mock service response
        when(orderService.getAllOrders()).thenReturn(List.of(dto));

        // Perform request
        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk());
    }
}
