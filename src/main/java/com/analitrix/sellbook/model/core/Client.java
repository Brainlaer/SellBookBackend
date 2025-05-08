package com.analitrix.sellbook.model.core;

import com.analitrix.sellbook.model.security.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(
        name = "CORE_CLIENT",
        indexes = {
                @Index(name = "IDX_CLI_CORE_BUS_ID", columnList = "CORE_BUS_ID")
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client {
    @Id
    @Column(name = "CLI_ID")
    private UUID id = UUID.randomUUID();
    @Column(name = "CLI_DOCUMENT_TYPE")
    private String documentType;
    @Column(unique = true, name = "CLI_DOCUMENT_NUMBER")
    private int documentNumber;
    @Column(name = "CLI_FIRSTNAME")
    private String firstname;
    @Column(name = "CLI_LASTNAME")
    private String lastname;

    @OneToOne
    @JoinColumn(name = "CORE_USER_ID")
    private User user;
    @ManyToOne
    @JoinColumn(name = "CORE_BUS_ID")
    private Business business;
}
