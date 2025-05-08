package com.analitrix.sellbook.model.core.dto.book;

import com.analitrix.sellbook.model.config.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductPostDto {
    private UUID id;
    private String barCode;
    private String name;
    private String description;
    private double cost;
    private UUID statusId;
    private UUID subCategoryId;
    private UUID businessId;
}
