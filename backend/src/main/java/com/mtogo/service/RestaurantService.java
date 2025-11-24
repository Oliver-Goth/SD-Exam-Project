package com.mtogo.service;

import com.mtogo.adapter.RestaurantAdapter;
import com.mtogo.dto.RestaurantDTO;
import com.mtogo.model.Restaurant;
import com.mtogo.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    // ✔ Constructor injection (best practice, testable)
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<RestaurantDTO> getAllActiveRestaurants() {
        return restaurantRepository.findByActiveTrue()
                .stream()
                .map(RestaurantAdapter::toDTO)
                .collect(Collectors.toList());
    }

    public RestaurantDTO getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .map(RestaurantAdapter::toDTO)
                .orElse(null);
    }

    public RestaurantDTO createRestaurant(RestaurantDTO dto) {
        Restaurant restaurant = RestaurantAdapter.toEntity(dto);
        Restaurant saved = restaurantRepository.save(restaurant);
        return RestaurantAdapter.toDTO(saved);
    }

    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }
}
