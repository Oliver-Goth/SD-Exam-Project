package com.mtogo.controller;

import com.mtogo.dto.DeliveryAgentDTO;
import com.mtogo.service.DeliveryAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agents")
@CrossOrigin(origins = "http://localhost:3000")
public class DeliveryAgentController {

    @Autowired
    private DeliveryAgentService deliveryAgentService;

    @GetMapping("/active")
    public List<DeliveryAgentDTO> getActiveAgents() {
        return deliveryAgentService.getAllActiveAgents();
    }
}
