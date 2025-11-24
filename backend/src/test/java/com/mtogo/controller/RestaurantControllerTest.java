package com.mtogo.controller;

import com.mtogo.dto.RestaurantDTO;
import com.mtogo.service.RestaurantService;
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

@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RestaurantService restaurantService;

    @Test
    @WithMockUser
    void getActiveRestaurants_returnsList() throws Exception {
        Mockito.when(restaurantService.getAllActiveRestaurants())
                .thenReturn(List.of(new RestaurantDTO(1L, "Rest", "r@b.com", "555", "City", true)));

        mockMvc.perform(get("/api/restaurants"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].active").value(true));
    }

    @Test
    @WithMockUser
    void getRestaurantById_found_returnsRestaurant() throws Exception {
        Mockito.when(restaurantService.getRestaurantById(1L))
                .thenReturn(new RestaurantDTO(1L, "Rest", "r@b.com", "555", "City", true));

        mockMvc.perform(get("/api/restaurants/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Rest"));
    }

    @Test
    @WithMockUser
    void getRestaurantById_notFound_returns200WithNull() throws Exception {
        Mockito.when(restaurantService.getRestaurantById(10L)).thenReturn(null);

        mockMvc.perform(get("/api/restaurants/10"))
                .andExpect(status().isOk());
    }
}
