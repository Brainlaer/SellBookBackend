package com.analitrix.sellbook.entity;


import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "libros")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Libro {
	
	@Id
	@Column(unique = true)
	private Long id;
	private String titulo;
	@Column(name="anio_publicacion")
	private Long anioPublicacion;
	private boolean disponibilidad;
	private Long unidades;
	private String editorial;
	private double costo;
	private String autor;
	private String image;
	@Temporal(TemporalType.TIMESTAMP)
	@Column (name="fecha_modificacion")
	private Date fechaModificacion;
	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;
	
    @PrePersist
    public void prePersist() {
        modificar();
        establecerdisponible();
    }
    
    public boolean establecerdisponible() {
        if(unidades>=1) {
        	return this.disponibilidad=true;
        }else {
        	return this.disponibilidad=false;
        }
    }
    
    public Long vender() {
    	return this.unidades=this.unidades-1;
    }
    
    public Date modificar() {
    	return this.fechaModificacion = new Date();
    }
}
