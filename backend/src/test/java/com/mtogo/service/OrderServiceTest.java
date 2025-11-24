package com.mtogo.service;

import com.mtogo.dto.CustomerDTO;
import com.mtogo.dto.OrderDTO;
import com.mtogo.dto.RestaurantDTO;
import com.mtogo.model.Customer;
import com.mtogo.model.Order;
import com.mtogo.model.Restaurant;
import com.mtogo.repository.CustomerRepository;
import com.mtogo.repository.OrderRepository;
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
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private OrderService orderService;

    // ✅ EP (valid partition): valid customer & restaurant → success
    @Test
    void createOrder_validData_savesAndReturnsDto() {
        // Arrange
        CustomerDTO customerDTO = new CustomerDTO(1L, "John", "john@test.com", "12345", "City");
        RestaurantDTO restaurantDTO = new RestaurantDTO(2L, "Rest", "rest@test.com", "56789", "City", true);

        OrderDTO dto = new OrderDTO();
        dto.setCustomer(customerDTO);
        dto.setRestaurant(restaurantDTO);
        dto.setStatus("PENDING");
        dto.setTotal(BigDecimal.TEN); // normal EP value

        Customer customer = new Customer("John", "john@test.com", "12345", "City");
        customer.setId(1L);

        Restaurant restaurant = new Restaurant();
        restaurant.setId(2L);
        restaurant.setName("Rest");

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(restaurantRepository.findById(2L)).thenReturn(Optional.of(restaurant));

        Order savedOrder = new Order();
        savedOrder.setId(100L);
        savedOrder.setStatus("PENDING");
        savedOrder.setTotal(BigDecimal.TEN);
        savedOrder.setCustomer(customer);
        savedOrder.setRestaurant(restaurant);

        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        // Act
        OrderDTO result = orderService.createOrder(dto);

        // Assert
        assertNotNull(result);
        assertEquals(100L, result.getId());
        assertEquals("PENDING", result.getStatus());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    // ✅ EP (invalid partition): missing customer
    @Test
    void createOrder_missingCustomer_throwsException() {
        OrderDTO dto = new OrderDTO();
        dto.setRestaurant(new RestaurantDTO(2L, "Rest", "rest@test.com", "56789", "City", true));

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> orderService.createOrder(dto)
        );

        assertTrue(ex.getMessage().contains("Customer ID"));
    }

    // ✅ EP (invalid partition): customer not found in DB
    @Test
    void createOrder_customerNotFound_throwsException() {
        CustomerDTO customerDTO = new CustomerDTO(99L, "Missing", "x@test.com", "000", "Nowhere");
        RestaurantDTO restaurantDTO = new RestaurantDTO(2L, "Rest", "rest@test.com", "56789", "City", true);

        OrderDTO dto = new OrderDTO();
        dto.setCustomer(customerDTO);
        dto.setRestaurant(restaurantDTO);

        when(customerRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> orderService.createOrder(dto));
    }

    // ✅ BVA: total = 0 (boundary)
    @Test
    void createOrder_totalZero_boundaryValueAccepted() {
        CustomerDTO customerDTO = new CustomerDTO(1L, "John", "john@test.com", "12345", "City");
        RestaurantDTO restaurantDTO = new RestaurantDTO(2L, "Rest", "rest@test.com", "56789", "City", true);

        OrderDTO dto = new OrderDTO();
        dto.setCustomer(customerDTO);
        dto.setRestaurant(restaurantDTO);
        dto.setStatus("PENDING");
        dto.setTotal(BigDecimal.ZERO); // BVA

        Customer customer = new Customer("John", "john@test.com", "12345", "City");
        customer.setId(1L);

        Restaurant restaurant = new Restaurant();
        restaurant.setId(2L);
        restaurant.setName("Rest");

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(restaurantRepository.findById(2L)).thenReturn(Optional.of(restaurant));
        when(orderRepository.save(any(Order.class))).thenReturn(new Order());

        assertDoesNotThrow(() -> orderService.createOrder(dto));
    }

    // ✅ Guest order flow: uses CustomerService mock + repositories
    @Test
    void createGuestOrder_createsCustomerAndOrder() {
        // guest DTO without ID
        CustomerDTO guest = new CustomerDTO(null, "Guest", "guest@test.com", "9999", "GuestCity");

        OrderDTO dto = new OrderDTO();
        dto.setCustomer(guest);
        dto.setRestaurant(new RestaurantDTO(2L, "Rest", "rest@test.com", "56789", "City", true));
        dto.setStatus("PENDING");
        dto.setTotal(BigDecimal.TEN);

        // customer service returns saved guest with ID
        CustomerDTO savedGuest = new CustomerDTO(5L, "Guest", "guest@test.com", "9999", "GuestCity");
        when(customerService.createGuestCustomer(
                guest.getName(), guest.getPhone(), guest.getEmail(), guest.getAddress()
        )).thenReturn(savedGuest);

        Customer customerEntity = new Customer("Guest", "guest@test.com", "9999", "GuestCity");
        customerEntity.setId(5L);

        Restaurant restaurant = new Restaurant();
        restaurant.setId(2L);
        restaurant.setName("Rest");

        when(customerRepository.findById(5L)).thenReturn(Optional.of(customerEntity));
        when(restaurantRepository.findById(2L)).thenReturn(Optional.of(restaurant));

        Order savedOrder = new Order();
        savedOrder.setId(123L);
        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        // Act
        OrderDTO result = orderService.createGuestOrder(dto);

        // Assert
        assertNotNull(result);
        assertEquals(123L, result.getId());
        verify(customerService, times(1))
                .createGuestCustomer(guest.getName(), guest.getPhone(), guest.getEmail(), guest.getAddress());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    // Simple test to increase coverage on getAllOrders
    @Test
    void getAllOrders_returnsMappedDtos() {
        Order order = new Order();
        order.setId(1L);
        order.setStatus("PENDING");
        when(orderRepository.findAll()).thenReturn(List.of(order));

        var list = orderService.getAllOrders();

        assertEquals(1, list.size());
        assertEquals(1L, list.get(0).getId());
    }
}
