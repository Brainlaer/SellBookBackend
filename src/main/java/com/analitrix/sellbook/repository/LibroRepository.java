package com.analitrix.sellbook.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.analitrix.sellbook.entity.Categoria;
import com.analitrix.sellbook.entity.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{
	
	List<Libro> findAllByOrderByFechaModificacionDesc();
	List<Libro> findByTituloContainingIgnoreCase (String cadena);
	List<Libro> findByAutorContainingIgnoreCase (String cadena);
	List<Libro> findByCategoria(Categoria categoria);
}
