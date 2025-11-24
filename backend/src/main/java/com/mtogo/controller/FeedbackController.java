package com.mtogo.controller;

import com.mtogo.dto.FeedbackDTO;
import com.mtogo.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin(origins = "http://localhost:3000")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/order/{orderId}")
    public FeedbackDTO createFeedback(@RequestBody FeedbackDTO dto, @PathVariable Long orderId) {
        return feedbackService.createFeedback(dto, orderId);
    }

    @GetMapping("/order/{orderId}")
    public FeedbackDTO getFeedbackByOrder(@PathVariable Long orderId) {
        return feedbackService.getFeedbackByOrder(orderId);
    }
}
