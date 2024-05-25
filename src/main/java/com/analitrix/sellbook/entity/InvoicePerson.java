package com.analitrix.sellbook.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "invoice_people")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoicePerson {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="id_person")
    private Long idPerson;
    @Column(name="full_name")
    private String fullName;
    private Long phone;
    private String mail;
    @Column(name = "home_address")
    private String homeAddress;


}
