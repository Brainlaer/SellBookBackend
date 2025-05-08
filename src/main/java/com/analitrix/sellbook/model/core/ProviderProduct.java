package com.analitrix.sellbook.model.core;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(
        name = "CORE_PROVIDER_PRODUCT",
        indexes = {
                @Index(name = "IDX_PROV_PROD_CORE_BUS_ID", columnList = "CORE_BUS_ID")
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProviderProduct {
    @Id
    @Column(name = "PROV_PROD_ID")
    private UUID id= UUID.randomUUID();
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PROV_PROD_START_DATE")
    private Date startDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PROV_PROD_END_DATE")
    private Date endDate;
    @Column(name = "PROV_PROD_COST")
    private double cost;

    @ManyToOne
    @JoinColumn(name = "CORE_PROV_ID")
    private Provider provider;
    @ManyToOne
    @JoinColumn(name = "CORE_PROD_ID")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "CORE_BUS_ID")
    private Business business;
}
