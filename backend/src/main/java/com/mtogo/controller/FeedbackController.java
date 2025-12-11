package com.mtogo.controller;

import com.mtogo.dto.FeedbackDTO;
import com.mtogo.service.FeedbackService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(
    name = "Feedback",
    description = "Customer feedback submissions and feedback retrieval for completed orders"
)
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    // ------------------------------------------------------
    // POST /api/feedback/order/{orderId}
    // ------------------------------------------------------
    @PostMapping("/order/{orderId}")
    @Operation(
        summary = "Submit feedback for an order",
        description = "Creates a new feedback entry for a completed order. The order must belong to the customer providing the feedback."
    )
    @ApiResponse(
        responseCode = "201",
        description = "Feedback submitted successfully",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = FeedbackDTO.class)
        )
    )
    @ApiResponse(
        responseCode = "400",
        description = "Invalid feedback submission"
    )
    @ApiResponse(
        responseCode = "404",
        description = "Order not found"
    )
    public FeedbackDTO createFeedback(@RequestBody FeedbackDTO dto, @PathVariable Long orderId) {
        return feedbackService.createFeedback(dto, orderId);
    }

    // ------------------------------------------------------
    // GET /api/feedback/order/{orderId}
    // ------------------------------------------------------
    @GetMapping("/order/{orderId}")
    @Operation(
        summary = "Get feedback for an order",
        description = "Retrieves existing feedback linked to the provided order ID."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Feedback retrieved successfully",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = FeedbackDTO.class)
        )
    )
    @ApiResponse(
        responseCode = "404",
        description = "Feedback not found for the given order"
    )
    public FeedbackDTO getFeedbackByOrder(@PathVariable Long orderId) {
        return feedbackService.getFeedbackByOrder(orderId);
    }
}
