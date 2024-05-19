package com.analitrix.sellbook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name="invoice_books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceBook {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String title;
	@Column(nullable = true)
	private double cost;
	@ManyToOne
	@JoinColumn(name = "id_invoice")
	private Invoice invoice;
}
