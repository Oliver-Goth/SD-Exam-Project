package com.mtogo.service;

import com.mtogo.adapter.DeliveryAgentAdapter;
import com.mtogo.dto.DeliveryAgentDTO;
import com.mtogo.repository.DeliveryAgentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryAgentService {

    private final DeliveryAgentRepository deliveryAgentRepository;

    // 🔥 Constructor injection — clean, testable, recommended
    public DeliveryAgentService(DeliveryAgentRepository deliveryAgentRepository) {
        this.deliveryAgentRepository = deliveryAgentRepository;
    }

    public List<DeliveryAgentDTO> getAllActiveAgents() {
        return deliveryAgentRepository.findByActiveTrue()
                .stream()
                .map(DeliveryAgentAdapter::toDTO)
                .collect(Collectors.toList());
    }
}
