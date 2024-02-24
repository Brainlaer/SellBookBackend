package com.analitrix.sellbook.entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "facturas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Factura {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long usuario;
	@Column(name = "fecha_expedicion")
	private Date fechaExpedicion;
	@Column(name = "costo_total")
	private double costoTotal;
	@OneToOne
	@JoinColumn(name = "id_guia")
	private Guia guia;
	
	@PrePersist
	public void prePersist() {
		expedir();
	}
	
	public Date expedir() {
		return this.fechaExpedicion=new Date();
	}
}
