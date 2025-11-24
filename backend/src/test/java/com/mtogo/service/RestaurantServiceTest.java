package com.mtogo.service;

import com.mtogo.dto.RestaurantDTO;
import com.mtogo.model.Restaurant;
import com.mtogo.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantServiceTest {

    private RestaurantRepository restaurantRepository;
    private RestaurantService restaurantService;

    @BeforeEach
    void setup() {
        restaurantRepository = mock(RestaurantRepository.class);
        restaurantService = new RestaurantService(restaurantRepository);
    }

    // ----------------------------------------------------------
    // 1. Test getAllActiveRestaurants()
    // ----------------------------------------------------------
    @Test
    void testGetAllActiveRestaurants() {
        Restaurant r = new Restaurant();
        r.setId(1L);
        r.setName("Pizza Place");
        r.setActive(true);

        when(restaurantRepository.findByActiveTrue()).thenReturn(List.of(r));

        List<RestaurantDTO> result = restaurantService.getAllActiveRestaurants();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Pizza Place", result.get(0).getName());

        verify(restaurantRepository).findByActiveTrue();
    }

    // ----------------------------------------------------------
    // 2. Test getRestaurantById() - found
    // ----------------------------------------------------------
    @Test
    void testGetRestaurantById_found() {
        Restaurant r = new Restaurant();
        r.setId(5L);
        r.setName("Test Restaurant");

        when(restaurantRepository.findById(5L)).thenReturn(Optional.of(r));

        RestaurantDTO dto = restaurantService.getRestaurantById(5L);

        assertNotNull(dto);
        assertEquals(5L, dto.getId());
        assertEquals("Test Restaurant", dto.getName());
    }

    // ----------------------------------------------------------
    // 3. Test getRestaurantById() - NOT found
    // ----------------------------------------------------------
    @Test
    void testGetRestaurantById_notFound() {
        when(restaurantRepository.findById(100L)).thenReturn(Optional.empty());

        RestaurantDTO dto = restaurantService.getRestaurantById(100L);

        assertNull(dto);
    }

    // ----------------------------------------------------------
    // 4. Test createRestaurant()
    // ----------------------------------------------------------
    @Test
    void testCreateRestaurant() {
        RestaurantDTO dto = new RestaurantDTO();
        dto.setName("New Restaurant");
        dto.setActive(true);

        Restaurant saved = new Restaurant();
        saved.setId(10L);
        saved.setName("New Restaurant");
        saved.setActive(true);

        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(saved);

        RestaurantDTO result = restaurantService.createRestaurant(dto);

        assertNotNull(result);
        assertEquals(10L, result.getId());
        assertEquals("New Restaurant", result.getName());
        assertTrue(result.isActive());

        verify(restaurantRepository).save(any(Restaurant.class));
    }

    // ----------------------------------------------------------
    // 5. Test deleteRestaurant()
    // ----------------------------------------------------------
    @Test
    void testDeleteRestaurant() {
        restaurantService.deleteRestaurant(7L);

        verify(restaurantRepository).deleteById(7L);
    }
}
