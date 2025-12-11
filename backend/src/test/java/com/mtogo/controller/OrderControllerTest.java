package com.mtogo.controller;

import com.mtogo.dto.OrderDTO;
import com.mtogo.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    @WithMockUser(username = "testuser", password = "testpassword")
    void getOrderById_returnsOrderDTO() throws Exception {
        OrderDTO dto = new OrderDTO();
        dto.setId(1L);
        dto.setStatus("PENDING");
        dto.setCreatedAt(LocalDateTime.now());
        dto.setTotal(BigDecimal.TEN);

        Mockito.when(orderService.getOrderById(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status").value("PENDING"))
                .andExpect(jsonPath("$.total").value(10));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword")
    void getOrderById_notFound_returns200WithNull() throws Exception {
        Mockito.when(orderService.getOrderById(99L)).thenReturn(null);

        mockMvc.perform(get("/api/orders/99"))
                .andExpect(status().isOk());
    }

    @Test
    void getOrderById_unauthorized_returns401() throws Exception {
        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isUnauthorized());
    }
}
