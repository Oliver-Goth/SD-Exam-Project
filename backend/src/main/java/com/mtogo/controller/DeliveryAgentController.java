package com.mtogo.controller;

import com.mtogo.dto.DeliveryAgentDTO;
import com.mtogo.service.DeliveryAgentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agents")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(
    name = "Delivery Agents",
    description = "Endpoints for managing and retrieving active delivery agents"
)
public class DeliveryAgentController {

    @Autowired
    private DeliveryAgentService deliveryAgentService;

    // ------------------------------------------------------
    // GET /api/agents/active
    // ------------------------------------------------------
    @GetMapping("/active")
    @Operation(
        summary = "Get all active delivery agents",
        description = "Returns a list of all delivery agents currently marked as active and available for deliveries."
    )
    @ApiResponse(
        responseCode = "200",
        description = "List of active agents retrieved successfully",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = DeliveryAgentDTO.class)
        )
    )
    public List<DeliveryAgentDTO> getActiveAgents() {
        return deliveryAgentService.getAllActiveAgents();
    }
}
