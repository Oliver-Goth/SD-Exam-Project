package com.mtogo.adapter;

import com.mtogo.dto.MenuItemDTO;
import com.mtogo.model.MenuItem;

public class MenuItemAdapter {

    public static MenuItemDTO toDTO(MenuItem item) {
        if (item == null) return null;
        return new MenuItemDTO(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getPrice(),
                item.isAvailable()
        );
    }

    public static MenuItem toEntity(MenuItemDTO dto) {
        if (dto == null) return null;
        MenuItem item = new MenuItem();
        item.setId(dto.getId());
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setPrice(dto.getPrice());
        item.setAvailable(dto.isAvailable());
        return item;
    }
}
