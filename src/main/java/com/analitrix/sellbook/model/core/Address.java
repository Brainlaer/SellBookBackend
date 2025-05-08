package com.analitrix.sellbook.model.core;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(
        name = "CORE_ADDRESS",
        indexes = {
                @Index(name = "IDX_ADDR_CORE_BUS_ID", columnList = "CORE_BUS_ID")
        }
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {
    @Id
    @Column(name = "ADDR_ID")
    private UUID id = UUID.randomUUID();
    @Column(name = "ADDR_STREET_TYPE")
    private String streetType;
    @Column(name = "ADDR_STREET_NUMBER")
    private String streetNumber;
    @Column(name = "ADDR_COMPLEMENT")
    private String complement;
    @Column(name = "ADDR_COUNTRY")
    private String country;
    @Column(name = "ADDR_CITY")
    private String city;
    @Column(name = "ADDR_NEIGHBORHOOD")
    private String neighborhood;

    @ManyToOne
    @JoinColumn(name = "CORE_BUS_ID")
    private Business business;
}
