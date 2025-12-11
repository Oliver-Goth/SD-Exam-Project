package com.mtogo.service;

import com.mtogo.adapter.OrderAdapter;
import com.mtogo.dto.CustomerDTO;
import com.mtogo.dto.OrderDTO;
import com.mtogo.model.Customer;
import com.mtogo.model.Order;
import com.mtogo.model.Restaurant;
import com.mtogo.repository.CustomerRepository;
import com.mtogo.repository.OrderRepository;
import com.mtogo.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    private final CustomerService customerService;

    public OrderService(OrderRepository orderRepository,
            CustomerRepository customerRepository,
            RestaurantRepository restaurantRepository,
            CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
        this.customerService = customerService;
    }

    public OrderDTO createGuestOrder(OrderDTO dto) {
        CustomerDTO guest = dto.getCustomer();
        if (guest == null) {
            throw new IllegalArgumentException("Guest customer information is missing");
        }

        CustomerDTO savedCustomer = customerService.createGuestCustomer(
                guest.getName(),
                guest.getPassword(),
                guest.getEmail(),
                guest.getPhoneNumber(),
                guest.getDateOfBirth());

        dto.setCustomer(savedCustomer);
        return createOrder(dto);
    }

    public OrderDTO createOrder(OrderDTO dto) {
        if (dto.getCustomer() == null || dto.getCustomer().getId() == null) {
            throw new IllegalArgumentException("Customer ID missing in OrderDTO");
        }
        if (dto.getRestaurant() == null || dto.getRestaurant().getId() == null) {
            throw new IllegalArgumentException("Restaurant ID missing in OrderDTO");
        }

        Customer customer = customerRepository.findById(dto.getCustomer().getId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurant().getId())
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

        Order order = OrderAdapter.toEntity(dto);
        order.setCustomer(customer);
        order.setRestaurant(restaurant);

        Order saved = orderRepository.save(order);
        return OrderAdapter.toDTO(saved);
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(OrderAdapter::toDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(OrderAdapter::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    public List<OrderDTO> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status)
                .stream()
                .map(OrderAdapter::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new IllegalArgumentException("Order does not exist");
        }
        orderRepository.deleteById(id);
    }
}
