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
        name = "CORE_SUBCATEGORY",
        indexes = {
                @Index(name = "IDX_SUBCAT_CORE_BUS_ID", columnList = "CORE_BUS_ID")
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SubCategory {
    @Id
    @Column(name = "SUBCAT_ID")
    private UUID id= UUID.randomUUID();
    @Column(name = "SUBCAT_NAME")
    private String name;
    @Column(name = "SUBCAT_DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "CFG_STS_ID")
    private Status status;
    @ManyToOne
    @JoinColumn(name = "CORE_CAT_ID")
    private Category category;
}
