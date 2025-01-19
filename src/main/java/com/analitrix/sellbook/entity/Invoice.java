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
    private Date creationDate;
    @Column(name = "total_cost")
    private double totalCost;

    @OneToOne
    @JoinColumn(name = "invoice_user_id")
    private InvoiceUser invoiceUser;
    @OneToOne
    @JoinColumn(name = "tracking_id")
    private Tracking tracking;

    @PrePersist
    public void prePersist() {
        expedite();
    }

    public Date expedite() {
        return this.creationDate=new Date();
    }
}
