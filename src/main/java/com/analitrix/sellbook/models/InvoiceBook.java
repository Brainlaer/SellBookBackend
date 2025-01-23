package com.analitrix.sellbook.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name="invoice_books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceBook {

	@Id
	private String id= UUID.randomUUID().toString();
	private Long isxn;
	private String title;
	@Column(nullable = true)
	private double cost;

	@ManyToOne
	@JoinColumn(name = "invoice_id")
	private Invoice invoice;
}
