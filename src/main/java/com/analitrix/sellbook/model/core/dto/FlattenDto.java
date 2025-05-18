package com.analitrix.sellbook.model.core.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class FlattenDto {
    private UUID id;
    private String name;
}
