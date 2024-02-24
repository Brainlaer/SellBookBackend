package com.analitrix.sellbook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Id;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
	
	@Id
	@Column(unique = true)
	private Long id;
	private String nombre;
	private String apellido;
	@Column(unique = true)
	private Long telefono;
	@Column(unique = true)
	private String email;
	private String contraseña;
	private String direccionDomiciliaria;
}
