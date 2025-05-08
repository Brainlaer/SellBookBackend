package com.analitrix.sellbook.model.core;

import com.analitrix.sellbook.model.config.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(
        name = "CORE_PURCHASE",
        indexes = {
                @Index(name = "IDX_PUR_CORE_BUS_ID", columnList = "CORE_BUS_ID")
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Purchase {
    @Id
    @Column(name = "PUR_ID")
    private UUID id= UUID.randomUUID();
    @ManyToOne
    @JoinColumn(name = "CFG_STS_ID")
    private Status status;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PUR_CREATION_DATE")
    private Date creationDate = new Date();
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PUR_DELIVERY_DATE")
    private Date deliveryDate = new Date();
    @Column(name = "PUR_TOTAL_COST")
    private double totalCost;

    @ManyToOne
    @JoinColumn(name = "CORE_PROV_ID")
    private Provider provider;
    @ManyToOne
    @JoinColumn(name = "CORE_BUS_ID")
    private Business business;
}
