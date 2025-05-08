package com.analitrix.sellbook.model.core;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(
        name = "CORE_PURCHASE_DETAIL",
        indexes = {
                @Index(name = "IDX_PUR_DET_CORE_BUS_ID", columnList = "CORE_BUS_ID")
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PurchaseDetail {
    @Id
    @Column(name = "PUR_DET_ID")
    private UUID id= UUID.randomUUID();
    @Column(name = "PUR_DET_QUANTITY")
    private int quantity;
    @Column(name = "PUR_DET_COST")
    private double cost;

    @ManyToOne
    @JoinColumn(name = "CORE_PUR_ID")
    private Purchase purchase;
    @ManyToOne
    @JoinColumn(name = "CORE_PROD_ID")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "CORE_BUS_ID")
    private Business business;
}
