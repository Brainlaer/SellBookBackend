package com.analitrix.sellbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.analitrix.sellbook.entity.Categoria;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long>{
	
}
