package com.analitrix.sellbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.analitrix.sellbook.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
}
