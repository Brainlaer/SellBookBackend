package com.analitrix.sellbook.model.core;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(
        name = "CORE_PARAMETER",
        indexes = {
                @Index(name = "IDX_PARAM_CORE_BUS_ID", columnList = "CORE_BUS_ID")
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Parameter {
    @Id
    @Column(name = "PARAM_ID")
    private UUID id= UUID.randomUUID();
    @Column(name = "PARAM_NAME")
    private String name;
    @Column(name = "PARAM_VALUE")
    private String value;
    @ManyToOne
    @JoinColumn(name = "CORE_BUS_ID")
    private Business business;
}
