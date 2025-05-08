package com.analitrix.sellbook.model.core;

import com.analitrix.sellbook.model.config.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(
        name = "CORE_CLIENT_ADDRESS",
        indexes = {
                @Index(name = "IDX_CLI_ADDR_CORE_BUS_ID", columnList = "CORE_BUS_ID")
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientAddress {
    @Id
    @Column(name = "CLI_ADDR_ID")
    private UUID id = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "CORE_CLI_ID")
    private Client client;
    @OneToOne
    @JoinColumn(name = "CORE_ADDR_ID")
    private Address address;
    @ManyToOne
    @JoinColumn(name = "CORE_BUS_ID")
    private Business business;
}
