package com.analitrix.sellbook.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "invoice_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceUser {
    @Id
    private String id= UUID.randomUUID().toString();
    @Column(name="user_id", unique = true)
    private String user;
    private Long documentNumber;
    private String fullName;
    @Column(unique = true)
    private Long phone;
    @Column(unique = true)
    private String mail;
    @Column(name = "home_address")
    private String homeAddress;


}
