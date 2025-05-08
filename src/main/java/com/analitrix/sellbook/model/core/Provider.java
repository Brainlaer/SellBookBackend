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
        name = "CORE_PROVIDER",
        indexes = {
                @Index(name = "IDX_PROV_CORE_BUS_ID", columnList = "CORE_BUS_ID")
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Provider {
    @Id
    @Column(name = "PROV_ID")
    private UUID id = UUID.randomUUID();
    @Column(name = "PROV_TYPE")
    private String type;
    @Column(name = "PROV_NAME")
    private String name;
    @Column(name = "PROV_DOCUMENT_TYPE")
    private String documentType;
    @Column(name = "PROV_DOCUMENT_NUMBER")
    private String documentNumber;
    @Column(name = "PROV_PHONE")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "CFG_STS_ID")
    private Status status;
    @ManyToOne
    @JoinColumn(name = "CORE_BUS_ID")
    private Business business;
}
