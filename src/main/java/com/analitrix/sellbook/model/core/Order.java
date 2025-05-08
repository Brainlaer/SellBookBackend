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
        name = "CORE_ORDER",
        indexes = {
                @Index(name = "IDX_ORD_CORE_BUS_ID", columnList = "CORE_BUS_ID")
        }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @Column(name = "ORD_ID")
    private UUID id = UUID.randomUUID();
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ORD_CREATION_DATE")
    private Date creationDate = new Date();
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ORD_DELIVERY_DATE")
    private Date deliveryDate;
    @Column(name = "ORD_TOTAL_COST")
    private double totalCost;
    @Column(name = "ORD_TOTAL_WEIGHT")
    private double totalWeight;

    @ManyToOne
    @JoinColumn(name = "CFG_STS_ID")
    private Status status;
    @OneToOne
    @JoinColumn(name = "CORE_ADDR_ID")
    private Address address;
    @ManyToOne
    @JoinColumn(name = "CORE_CLI_ID")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "CORE_BUS_ID")
    private Business business;
}
