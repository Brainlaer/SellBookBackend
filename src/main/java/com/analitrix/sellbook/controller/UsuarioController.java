package com.analitrix.sellbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.analitrix.sellbook.dto.UsuarioIdDto;
import com.analitrix.sellbook.entity.Usuario;
import com.analitrix.sellbook.service.UsuarioService;

@RestController
@RequestMapping ("/sellbook/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/id")
	public ResponseEntity<Usuario> findById(@RequestBody UsuarioIdDto usuarioIdDto){
		return usuarioService.findById(usuarioIdDto);
	}
	
	@PostMapping("/insertar")
	public ResponseEntity<String> insertUsuario(@RequestBody Usuario usuario) {
		return usuarioService.insertUsuario(usuario);
	}
	
	@PutMapping("/actualizar")
	public ResponseEntity<String> updateUsuario(@RequestBody Usuario usuario) {
		return usuarioService.updateUsuario(usuario);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {
		return usuarioService.deleteUsuario(id);
	}
}
