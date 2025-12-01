package com.mtogo.controller;

import com.mtogo.dto.PaymentDTO;
import com.mtogo.service.PaymentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(
    name = "Payments",
    description = "Payment processing, retrieval, and order payment management"
)
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // ------------------------------------------------------
    // GET /api/payments/order/{orderId}
    // ------------------------------------------------------
    @GetMapping("/order/{orderId}")
    @Operation(
        summary = "Get payment for an order",
        description = "Returns payment information associated with a specific order."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Payment retrieved successfully",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = PaymentDTO.class)
        )
    )
    @ApiResponse(
        responseCode = "404",
        description = "Payment not found for the given order"
    )
    public PaymentDTO getPaymentByOrder(@PathVariable Long orderId) {
        return paymentService.getPaymentByOrderId(orderId);
    }

    // ------------------------------------------------------
    // POST /api/payments
    // ------------------------------------------------------
    @PostMapping
    @Operation(
        summary = "Create a payment",
        description = "Creates a new payment entry for a customer order. This is typically called during the checkout process."
    )
    @ApiResponse(
        responseCode = "201",
        description = "Payment created successfully",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = PaymentDTO.class)
        )
    )
    @ApiResponse(
        responseCode = "400",
        description = "Invalid payment data"
    )
    public PaymentDTO createPayment(@RequestBody PaymentDTO dto) {
        return paymentService.createPayment(dto);
    }
}
