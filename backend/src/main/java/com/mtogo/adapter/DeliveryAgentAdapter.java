package com.mtogo.adapter;

import com.mtogo.dto.DeliveryAgentDTO;
import com.mtogo.model.DeliveryAgent;

public class DeliveryAgentAdapter {

    public static DeliveryAgentDTO toDTO(DeliveryAgent agent) {
        if (agent == null) return null;
        return new DeliveryAgentDTO(
                agent.getId(),
                agent.getName(),
                agent.getPhone(),
                agent.getVehicleType(),
                agent.isActive()
        );
    }

    public static DeliveryAgent toEntity(DeliveryAgentDTO dto) {
        if (dto == null) return null;
        DeliveryAgent agent = new DeliveryAgent();
        agent.setId(dto.getId());
        agent.setName(dto.getName());
        agent.setPhone(dto.getPhone());
        agent.setVehicleType(dto.getVehicleType());
        agent.setActive(dto.isActive());
        return agent;
    }
}
