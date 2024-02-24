package com.analitrix.sellbook.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.analitrix.sellbook.dto.LibroIdDto;
import com.analitrix.sellbook.entity.Factura;
import com.analitrix.sellbook.entity.Guia;
import com.analitrix.sellbook.entity.FacturaItem;
import com.analitrix.sellbook.entity.Libro;
import com.analitrix.sellbook.entity.Usuario;
import com.analitrix.sellbook.repository.FacturaRepository;
import com.analitrix.sellbook.repository.GuiaRepository;
import com.analitrix.sellbook.repository.ItemFacturaRepository;
import com.analitrix.sellbook.repository.LibroRepository;
import com.analitrix.sellbook.repository.UsuarioRepository;

@Service
public class FacturaService {

	@Autowired
	private FacturaRepository facturaRepository;

	@Autowired
	private ItemFacturaRepository itemFacturaRepository;

	@Autowired
	private LibroRepository libroRepository;

	@Autowired
	private GuiaRepository guiaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public ResponseEntity<String> crearFactura(Long usuarioIdDto, List<LibroIdDto> librosIdDtos) {
		Optional<Usuario> optionalusuario = usuarioRepository.findById(usuarioIdDto);

		if (optionalusuario.isPresent()) {
			Factura _factura = new Factura();
			Guia _guia = new Guia();
			guiaRepository.save(_guia);
			_factura.setUsuario(usuarioIdDto);
			_factura.setGuia(_guia);
			facturaRepository.save(_factura);

			if (facturaRepository.findById(_factura.getId()).isPresent()) {
				for (LibroIdDto libroIdDto : librosIdDtos) {
					Optional<Libro> optionalLibro = libroRepository.findById(libroIdDto.getId());
					Libro libro = optionalLibro.get();

					if (optionalLibro.isPresent() && libro.isDisponibilidad() == true) {
						FacturaItem _itemFactura = new FacturaItem();
						_itemFactura.setTitulo(libro.getTitulo());
						_itemFactura.setCosto(libro.getCosto());
						_itemFactura.setFactura(_factura);
						libro.vender();
						libro.establecerdisponible();
						_factura.setCostoTotal(_factura.getCostoTotal() + libro.getCosto());
						itemFacturaRepository.save(_itemFactura);
					} else {
						facturaRepository.deleteById(_factura.getId());
						guiaRepository.deleteById(_guia.getId());
						return new ResponseEntity<>("No se encontro algunos libros", HttpStatus.NOT_FOUND);
					}
				}
				return new ResponseEntity<>("Factura creada", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("No hay libros en la canasta", HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
		}
	}
}
