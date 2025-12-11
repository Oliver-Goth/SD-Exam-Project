package com.mtogo.service;

import com.mtogo.dto.FeedbackDTO;
import com.mtogo.model.Feedback;
import com.mtogo.model.Order;
import com.mtogo.repository.FeedbackRepository;
import com.mtogo.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FeedbackServiceTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private FeedbackService feedbackService;

    @Test
    void testCreateFeedback_OrderExists() {
        Long orderId = 1L;

        Order order = new Order();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        FeedbackDTO dto = new FeedbackDTO();
        Feedback saved = new Feedback();
        when(feedbackRepository.save(any())).thenReturn(saved);

        FeedbackDTO result = feedbackService.createFeedback(dto, orderId);

        assertNotNull(result);
        verify(orderRepository).findById(orderId);
        verify(feedbackRepository).save(any(Feedback.class));
    }

    @Test
    void testCreateFeedback_OrderNotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> feedbackService.createFeedback(new FeedbackDTO(), 1L));
    }
}
