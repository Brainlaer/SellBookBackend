package com.analitrix.sellbook.model.core;

import com.analitrix.sellbook.model.config.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "CORE_BUSINESS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Business {
    @Id
    @Column(name = "BUS_ID")
    private UUID id = UUID.randomUUID();
    @Column(name = "BUS_NAME", unique = true)
    private String name;
    @Column(name = "BUS_DOCUMENT_TYPE")
    private String documentType;
    @Column(name = "BUS_DOCUMENT_NUMBER", unique = true)
    private int documentNumber;

    @ManyToOne
    @JoinColumn(name = "CFG_STS_ID")
    private Status status;
}
