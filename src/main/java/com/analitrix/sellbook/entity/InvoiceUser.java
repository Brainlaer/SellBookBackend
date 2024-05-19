package com.analitrix.sellbook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "invoice_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceUser {

    @Id
    private Long id;
    @Column(name = "full_name")
    private String fullName;
    private Long phone;
    private String mail;
    @Column(name = "home_address")
    private String homeAddress;
}
