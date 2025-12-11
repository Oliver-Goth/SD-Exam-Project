package com.mtogo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtogo.dto.PaymentDTO;
import com.mtogo.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PaymentController.class)
class PaymentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    PaymentService paymentService;

    @Test
    @WithMockUser
    void createPayment_returnsSavedPayment() throws Exception {
        PaymentDTO dto = new PaymentDTO(1L, 1L, BigDecimal.TEN, "CARD", "PAID", null);

        Mockito.when(paymentService.createPayment(Mockito.any()))
                .thenReturn(dto);

        mockMvc.perform(post("/api/payments")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(dto))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.method").value("CARD"))
                .andExpect(jsonPath("$.amount").value(10));
    }
}
