package com.analitrix.sellbook.dto;

import com.analitrix.sellbook.entity.Categoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibroFindById {

    private Long id;
    private String titulo;
    private Long anioPublicacion;
    private Long unidades;
    private String editorial;
    private double costo;
    private String autor;
    private String image;
    private Categoria categoria;

}
