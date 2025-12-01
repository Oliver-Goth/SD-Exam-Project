package com.mtogo.controller;

import com.mtogo.dto.OrderDTO;
import com.mtogo.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(
    name = "Orders",
    description = "Operations related to order creation, retrieval, filtering, and deletion"
)
public class OrderController {

    @Autowired
    private OrderService orderService;

    // ------------------------------------------------------
    // GET /api/orders
    // ------------------------------------------------------
    @GetMapping
    @Operation(
        summary = "Get all orders",
        description = "Returns a list of all orders in the system."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Orders retrieved successfully",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = OrderDTO.class)
        )
    )
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    // ------------------------------------------------------
    // GET /api/orders/{id}
    // ------------------------------------------------------
    @GetMapping("/{id}")
    @Operation(
        summary = "Get order by ID",
        description = "Returns detailed information about a specific order."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Order retrieved successfully"
    )
    @ApiResponse(
        responseCode = "404",
        description = "Order not found"
    )
    public OrderDTO getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    // ------------------------------------------------------
    // GET /api/orders/status/{status}
    // ------------------------------------------------------
    @GetMapping("/status/{status}")
    @Operation(
        summary = "Get orders by status",
        description = "Returns orders filtered by their current status (e.g., Pending, Confirmed, Delivered)."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Filtered orders retrieved successfully"
    )
    public List<OrderDTO> getOrdersByStatus(@PathVariable String status) {
        return orderService.getOrdersByStatus(status);
    }

    // ------------------------------------------------------
    // POST /api/orders
    // ------------------------------------------------------
    @PostMapping
    @Operation(
        summary = "Create a new order",
        description = "Creates a new order for a registered customer."
    )
    @ApiResponse(
        responseCode = "201",
        description = "Order created successfully",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = OrderDTO.class)
        )
    )
    @ApiResponse(
        responseCode = "400",
        description = "Invalid order data provided"
    )
    public OrderDTO createOrder(@RequestBody OrderDTO dto) {
        return orderService.createOrder(dto);
    }

    // ------------------------------------------------------
    // POST /api/orders/guest
    // ------------------------------------------------------
    @PostMapping("/guest")
    @Operation(
        summary = "Create a guest order",
        description = "Creates an order for a guest user without requiring a customer account."
    )
    @ApiResponse(
        responseCode = "201",
        description = "Guest order created successfully"
    )
    @ApiResponse(
        responseCode = "400",
        description = "Invalid guest order data"
    )
    public OrderDTO createGuestOrder(@RequestBody OrderDTO dto) {
        return orderService.createGuestOrder(dto);
    }

    // ------------------------------------------------------
    // DELETE /api/orders/{id}
    // ------------------------------------------------------
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete an order",
        description = "Removes an order from the system by its ID."
    )
    @ApiResponse(
        responseCode = "204",
        description = "Order deleted successfully"
    )
    @ApiResponse(
        responseCode = "404",
        description = "Order not found"
    )
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
