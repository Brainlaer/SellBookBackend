package com.analitrix.sellbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.analitrix.sellbook.dto.UsuarioIdDto;
import com.analitrix.sellbook.entity.Usuario;
import com.analitrix.sellbook.service.UsuarioService;

@RestController
@RequestMapping ("/sellbook/usuario")
@CrossOrigin(origins = "*")
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
