package com.analitrix.sellbook.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.analitrix.sellbook.dto.UsuarioIdDto;
import com.analitrix.sellbook.entity.Usuario;
import com.analitrix.sellbook.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public ResponseEntity<Usuario> findById (UsuarioIdDto usuarioIdDto){
		Long id=usuarioIdDto.getId();
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
		
		if(optionalUsuario.isPresent()) {
			Usuario usuario = optionalUsuario.get();
			return new ResponseEntity<>(usuario, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<String> insertUsuario(Usuario usuario){
		
		if(usuarioRepository.findById(usuario.getId()).isEmpty()) {
			usuarioRepository.save(usuario);
			return new ResponseEntity<>("Insertado con exito", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("No se pudó insertar", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	public ResponseEntity<String> updateUsuario(Usuario usuario){
		
		if(usuarioRepository.findById(usuario.getId()).isPresent()) {
			Usuario _usuario = new Usuario(usuario.getId() ,usuario.getNombre() , usuario.getApellido(),  usuario.getTelefono(), usuario.getEmail(), usuario.getContraseña(), usuario.getDireccionDomiciliaria());
		usuarioRepository.save(_usuario);
		return new ResponseEntity<>("¡Actualizado con exito!", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("¡No se pudó actualizar!", HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<String> deleteUsuario(Long id){
		
		if(usuarioRepository.findById(id).isPresent()) {
			 usuarioRepository.deleteById(id);
			 return new ResponseEntity<>("¡Eliminado con exito!", HttpStatus.OK);
		}else{
			return new ResponseEntity<>("No se pudó eliminar", HttpStatus.NOT_FOUND);
		}
	}
}
