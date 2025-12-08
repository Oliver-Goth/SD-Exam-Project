package com.mtogo.service;

import com.mtogo.dto.DeliveryAgentDTO;
import com.mtogo.repository.DeliveryAgentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DeliveryAgentServiceTest {

    @Mock
    private DeliveryAgentRepository deliveryAgentRepository;

    @InjectMocks
    private DeliveryAgentService deliveryAgentService;

    @Test
    void testGetAllActiveAgents() {
        when(deliveryAgentRepository.findByActiveTrue()).thenReturn(List.of());

        List<DeliveryAgentDTO> result = deliveryAgentService.getAllActiveAgents();

        assertNotNull(result);
        verify(deliveryAgentRepository).findByActiveTrue();
    }
}
