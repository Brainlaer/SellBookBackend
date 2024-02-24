package com.analitrix.sellbook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.analitrix.sellbook.dto.LibroDto;
import com.analitrix.sellbook.entity.Categoria;
import com.analitrix.sellbook.entity.Libro;
import com.analitrix.sellbook.repository.LibroRepository;

@Service
public class LibroService {

	@Autowired
	private LibroRepository libroRepository;

	public ResponseEntity<String> insertLibro(Libro libro) {
		Optional<Libro> optionalLibro = libroRepository.findById(libro.getId());

		if (optionalLibro.isEmpty()) {
			libroRepository.save(libro);
			return new ResponseEntity<>("¡Guardado con exito!", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("¡No se pudó guardar!", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	public ResponseEntity<Libro> findById(Long id) {
		Optional<Libro> optionalLibro = libroRepository.findById(id);
		Libro libro = optionalLibro.get();

		if (libro != null) {
			return new ResponseEntity<>(libro, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<List<LibroDto>> findAllByOrderByFechaModificacionDesc() {
		List<Libro> listaLibros = libroRepository.findAllByOrderByFechaModificacionDesc();

		if (listaLibros.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			List<LibroDto> listaLibrosDto = new ArrayList<>();
			for (Libro libro : listaLibros) {
				LibroDto librodto = new LibroDto(libro.getTitulo(), libro.getAutor(), libro.getCosto(),
						libro.getImage());
				listaLibrosDto.add(librodto);
			}
			return new ResponseEntity<>(listaLibrosDto, HttpStatus.OK);
		}
	}

	public ResponseEntity<List<LibroDto>> findByTituloContainingIgnoreCase(String cadena) {
		List<Libro> listaLibros = libroRepository.findByTituloContainingIgnoreCase(cadena);

		if (listaLibros.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			List<LibroDto> listaLibrosdto = new ArrayList<>();
			for (Libro libro : listaLibros) {
				LibroDto libroDto = new LibroDto(libro.getTitulo(), libro.getAutor(), libro.getCosto(),
						libro.getImage());
				listaLibrosdto.add(libroDto);
			}
			return new ResponseEntity<>(listaLibrosdto, HttpStatus.OK);
		}
	}

	public ResponseEntity<List<LibroDto>> findByAutorContainingIgnoreCase(String cadena) {
		List<Libro> listaLibros = libroRepository.findByAutorContainingIgnoreCase(cadena);

		if (listaLibros.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			List<LibroDto> listaLibrosDto = new ArrayList<>();

			for (Libro libro : listaLibros) {
				LibroDto libroDto = new LibroDto(libro.getTitulo(), libro.getAutor(), libro.getCosto(),
						libro.getImage());
				listaLibrosDto.add(libroDto);
			}
			return new ResponseEntity<>(listaLibrosDto, HttpStatus.OK);
		}
	}

	public ResponseEntity<List<LibroDto>> findByCategoria(Categoria categoria) {
		List<Libro> listaLibros = libroRepository.findByCategoria(categoria);

		if (listaLibros.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			List<LibroDto> listaLibrosDto = new ArrayList<>();
			for (Libro libro : listaLibros) {
				LibroDto libroDto = new LibroDto(libro.getTitulo(), libro.getAutor(), libro.getCosto(),
						libro.getImage());
				listaLibrosDto.add(libroDto);
			}
			return new ResponseEntity<>(listaLibrosDto, HttpStatus.OK);
		}
	}

	public ResponseEntity<String> updateLibro(Libro libro) {
		Optional<Libro> optionalLibro = libroRepository.findById(libro.getId());
		Libro _libro = optionalLibro.get();

		if (_libro != null) {
			_libro.setId(libro.getId());
			_libro.setTitulo(libro.getTitulo());
			_libro.setAñoPublicacion(libro.getAñoPublicacion());
			_libro.setUnidades(libro.getUnidades());
			_libro.setEditorial(libro.getEditorial());
			_libro.setCosto(libro.getCosto());
			_libro.setAutor(libro.getAutor());
			_libro.setImage(libro.getImage());
			_libro.setCategoria(libro.getCategoria());
			_libro.modificar();
			_libro.establecerdisponible();
			libroRepository.save(_libro);
			return new ResponseEntity<>("¡Actualizado con exito!", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("¡No se pudó actualizar!", HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<String> deleteById(Long id) {
		Optional<Libro> optionalLibro = libroRepository.findById(id);
		Libro libro = optionalLibro.get();

		if (libro != null) {
			libroRepository.deleteById(id);
			return new ResponseEntity<>("¡Eliminado con exito!", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("¡No se pudó eliminar!", HttpStatus.NOT_FOUND);
		}
	}
}
