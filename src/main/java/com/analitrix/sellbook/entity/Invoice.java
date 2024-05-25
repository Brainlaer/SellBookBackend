package com.analitrix.sellbook.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "expedition_date")
    private Date expeditionDate;
    @Column(name = "total_cost")
    private double totalCost;
    private String tracking;
    @OneToOne
    @JoinColumn(name = "id_invoice_person")
    private InvoicePerson invoicePerson;


    @PrePersist
    public void prePersist() {
        expedite();
    }

    public Date expedite() {
        return this.expeditionDate=new Date();
    }
}
