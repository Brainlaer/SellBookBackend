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
@Table(name="factura_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacturaItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	@Column(nullable = true)
	private double costo;
	@ManyToOne
	@JoinColumn(name = "id_factura")
	private Factura factura;
}
