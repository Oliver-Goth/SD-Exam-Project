package com.mtogo.service;

import com.mtogo.adapter.MenuItemAdapter;
import com.mtogo.dto.MenuItemDTO;
import com.mtogo.model.MenuItem;
import com.mtogo.model.Restaurant;
import com.mtogo.repository.MenuItemRepository;
import com.mtogo.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MenuItemServiceTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private MenuItemService menuItemService;

    // ✅ EP (valid partition): create menu item with valid data
    @Test
    void createMenuItem_validData_savesAndReturnsDto() {
        // Arrange
        MenuItemDTO dto = new MenuItemDTO();
        dto.setName("Margherita Pizza");
        dto.setDescription("Classic pizza with tomato and mozzarella");
        dto.setPrice(BigDecimal.valueOf(12.99));

        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Pizza Palace");

        MenuItem savedItem = new MenuItem();
        savedItem.setId(10L);
        savedItem.setName("Margherita Pizza");
        savedItem.setDescription("Classic pizza with tomato and mozzarella");
        savedItem.setPrice(BigDecimal.valueOf(12.99));
        savedItem.setRestaurant(restaurant);

        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));
        when(menuItemRepository.save(any(MenuItem.class))).thenReturn(savedItem);

        // Act
        MenuItemDTO result = menuItemService.createMenuItem(dto, 1L);

        // Assert
        assertNotNull(result);
        assertEquals(10L, result.getId());
        assertEquals("Margherita Pizza", result.getName());
        assertEquals(BigDecimal.valueOf(12.99), result.getPrice());
        verify(restaurantRepository).findById(1L);
        verify(menuItemRepository).save(any(MenuItem.class));
    }

    // ✅ EP (invalid partition): restaurant not found
    @Test
    void createMenuItem_restaurantNotFound_throwsException() {
        // Arrange
        MenuItemDTO dto = new MenuItemDTO();
        dto.setName("Pasta");
        dto.setPrice(BigDecimal.valueOf(10.00));

        when(restaurantRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> menuItemService.createMenuItem(dto, 99L)
        );
    }

    // ✅ BVA: price = 0 (boundary)
    @Test
    void createMenuItem_priceZero_boundaryValueAccepted() {
        // Arrange
        MenuItemDTO dto = new MenuItemDTO();
        dto.setName("Free Item");
        dto.setPrice(BigDecimal.ZERO);

        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);

        MenuItem savedItem = new MenuItem();
        savedItem.setId(11L);
        savedItem.setName("Free Item");
        savedItem.setPrice(BigDecimal.ZERO);
        savedItem.setRestaurant(restaurant);

        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));
        when(menuItemRepository.save(any(MenuItem.class))).thenReturn(savedItem);

        // Act & Assert
        assertDoesNotThrow(() -> menuItemService.createMenuItem(dto, 1L));
    }

    // ✅ BVA: large price value
    @Test
    void createMenuItem_largePriceValue_accepted() {
        // Arrange
        MenuItemDTO dto = new MenuItemDTO();
        dto.setName("Premium Steak");
        dto.setPrice(BigDecimal.valueOf(999.99));

        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);

        MenuItem savedItem = new MenuItem();
        savedItem.setId(12L);
        savedItem.setName("Premium Steak");
        savedItem.setPrice(BigDecimal.valueOf(999.99));
        savedItem.setRestaurant(restaurant);

        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));
        when(menuItemRepository.save(any(MenuItem.class))).thenReturn(savedItem);

        // Act
        MenuItemDTO result = menuItemService.createMenuItem(dto, 1L);

        // Assert
        assertNotNull(result);
        assertEquals(BigDecimal.valueOf(999.99), result.getPrice());
    }

    // ✅ Test getMenuItemsByRestaurant
    @Test
    void getMenuItemsByRestaurant_returnsAllItemsForRestaurant() {
        // Arrange
        MenuItem item1 = new MenuItem();
        item1.setId(1L);
        item1.setName("Pizza");

        MenuItem item2 = new MenuItem();
        item2.setId(2L);
        item2.setName("Pasta");

        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);

        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));
        when(menuItemRepository.findByRestaurant(restaurant)).thenReturn(List.of(item1, item2));

        // Act
        List<MenuItemDTO> result = menuItemService.getMenuItemsByRestaurant(1L);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(restaurantRepository).findById(1L);
        verify(menuItemRepository).findByRestaurant(restaurant);
    }

    // ✅ Test getMenuItemsByRestaurant with empty list
    @Test
    void getMenuItemsByRestaurant_emptyRestaurant_returnsEmptyList() {
        // Arrange
        Restaurant restaurant = new Restaurant();
        restaurant.setId(99L);

        when(restaurantRepository.findById(99L)).thenReturn(Optional.of(restaurant));
        when(menuItemRepository.findByRestaurant(restaurant)).thenReturn(List.of());

        // Act
        List<MenuItemDTO> result = menuItemService.getMenuItemsByRestaurant(99L);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(restaurantRepository).findById(99L);
        verify(menuItemRepository).findByRestaurant(restaurant);
    }

    // ✅ Test menu item with null description
    @Test
    void createMenuItem_nullDescription_accepted() {
        // Arrange
        MenuItemDTO dto = new MenuItemDTO();
        dto.setName("Simple Item");
        dto.setDescription(null);
        dto.setPrice(BigDecimal.valueOf(5.00));

        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);

        MenuItem savedItem = new MenuItem();
        savedItem.setId(13L);
        savedItem.setName("Simple Item");
        savedItem.setDescription(null);
        savedItem.setPrice(BigDecimal.valueOf(5.00));
        savedItem.setRestaurant(restaurant);

        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));
        when(menuItemRepository.save(any(MenuItem.class))).thenReturn(savedItem);

        // Act
        MenuItemDTO result = menuItemService.createMenuItem(dto, 1L);

        // Assert
        assertNotNull(result);
        assertNull(result.getDescription());
    }
}
