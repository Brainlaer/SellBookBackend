package com.analitrix.sellbook.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    @Id
    private String id= UUID.randomUUID().toString();
    @Column(name = "expedition_date")
    private Date expeditionDate;
    @Column(name = "total_cost")
    private double totalCost;
    private String tracking;

    @OneToOne
    @JoinColumn(name = "invoice_user_id")
    private InvoiceUser invoiceUser;


    @PrePersist
    public void prePersist() {
        expedite();
    }

    public Date expedite() {
        return this.expeditionDate=new Date();
    }
}
