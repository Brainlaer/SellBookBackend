package com.analitrix.sellbook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibroDto{
	
	private String titulo;
	private String autor;
	private double costo;
	private String image;
}
