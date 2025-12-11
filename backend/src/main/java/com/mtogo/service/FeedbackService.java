package com.mtogo.service;

import com.mtogo.adapter.FeedbackAdapter;
import com.mtogo.dto.FeedbackDTO;
import com.mtogo.model.Feedback;
import com.mtogo.model.Order;
import com.mtogo.repository.FeedbackRepository;
import com.mtogo.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final OrderRepository orderRepository;

    // 🔥 Constructor injection — clean, testable, recommended
    public FeedbackService(FeedbackRepository feedbackRepository,
                           OrderRepository orderRepository) {
        this.feedbackRepository = feedbackRepository;
        this.orderRepository = orderRepository;
    }

    public FeedbackDTO createFeedback(FeedbackDTO dto, Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            throw new IllegalArgumentException("Order not found");
        }

        Feedback feedback = FeedbackAdapter.toEntity(dto);
        feedback.setOrder(order);

        return FeedbackAdapter.toDTO(feedbackRepository.save(feedback));
    }

    public FeedbackDTO getFeedbackByOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            throw new IllegalArgumentException("Order not found");
        }

        Feedback feedback = feedbackRepository.findByOrder(order);
        return FeedbackAdapter.toDTO(feedback);
    }
}
