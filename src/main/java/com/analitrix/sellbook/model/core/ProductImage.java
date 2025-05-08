package com.analitrix.sellbook.model.core;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(
        name = "CORE_PRODUCT_IMAGE",
        indexes = {
                @Index(name = "IDX_PROD_IMG_CORE_BUS_ID", columnList = "CORE_BUS_ID")
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductImage {
    @Id
    @Column(name = "PROD_IMG_ID")
    private UUID id= UUID.randomUUID();
    @Column(name = "PROD_IMG_URL")
    private String url;

    @ManyToOne
    @JoinColumn(name = "CORE_PROD_ID")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "CORE_BUS_ID")
    private Business business;
}
