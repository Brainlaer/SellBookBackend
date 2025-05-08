package com.analitrix.sellbook.service;

import java.util.*;

import com.analitrix.sellbook.model.core.dto.SortEnum;
import com.analitrix.sellbook.helpers.dto.ResponseHttp;
import com.analitrix.sellbook.model.core.Product;
import com.analitrix.sellbook.model.core.Category;
import com.analitrix.sellbook.model.core.SubCategory;
import com.analitrix.sellbook.model.core.dto.book.*;
import com.analitrix.sellbook.repository.ProductRepository;
import com.analitrix.sellbook.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	ModelMapper modelMapper = new ModelMapper();

	public ResponseEntity<ResponseHttp> create(ProductPostDto productPostDto) {
		Optional<SubCategory> subCategoryOptional = categoryRepository.findById(productPostDto.getCategoryId());
		if (categoryOptional.isEmpty())
			return new ResponseEntity<>(new ResponseHttp(204, "Categoria no encontrada"), HttpStatus.NO_CONTENT);
		if (productRepository.findByIsxn(productPostDto.getIsxn()).isPresent())
			return new ResponseEntity<>(new ResponseHttp(406, "El libro con el isxn: " + productPostDto.getIsxn() + ", ya existe."), HttpStatus.CONFLICT);
		Product product = modelMapper.map(productPostDto, Product.class);
		product.setId(UUID.randomUUID().toString());
		product.setCategory(categoryOptional.get());
		productRepository.save(product);
		return new ResponseEntity<>(new ResponseHttp(201, "Libro: " + productPostDto.getTitle() + ", Creado correctamente."), HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseHttp> createAll(List<ProductPostDto> booksPostDto) {
		List<Product> products = new ArrayList<>();
		for (ProductPostDto productPostDto :booksPostDto){
			Optional<Category> categoryOptional = categoryRepository.findById(productPostDto.getCategoryId());
			if (categoryOptional.isEmpty())
				return new ResponseEntity<>(new ResponseHttp(204, "Categoria no encontrada"), HttpStatus.NO_CONTENT);
			if (productRepository.findByIsxn(productPostDto.getIsxn()).isPresent())
				return new ResponseEntity<>(new ResponseHttp(406, "El libro con el isxn: " + productPostDto.getIsxn() + ", ya existe."), HttpStatus.CONFLICT);
			Product product = modelMapper.map(productPostDto, Product.class);
			product.setId(UUID.randomUUID().toString());
			product.setCategory(categoryOptional.get());
			products.add(product);
		}
		productRepository.saveAll(products);
		return new ResponseEntity<>(new ResponseHttp(201, "Libros creados correctamente."), HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseHttp> findOne(String id) {
		Optional<Product> optionalBook = productRepository.findById(id);
		if (optionalBook.isPresent()) {
			BookGetDto bookGetDto = modelMapper.map(optionalBook.get(), BookGetDto.class);
			return new ResponseEntity<>(new ResponseHttp(200, bookGetDto), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseHttp(404,"No existe libro con el id: "+id+"."),HttpStatus.NOT_FOUND);
		}
	}

	public Page<Product> findAll(BookRequestDto request) {
		Sort sort = null;
		if(request.getSort().equals(SortEnum.ASC)) {
			sort = Sort.by(Sort.Order.asc(request.getSortableColumn().toString()));
		}else if(request.getSort().equals(SortEnum.DESC)){
			sort = Sort.by(Sort.Order.desc(request.getSortableColumn().toString()));
		}
		Specification<Product> spec = BookSpecifications.filterBy(request.getIsxn(),request.getTitle(), request.getAuthor(), request.getEditorial(), request.getCategory());
		Pageable pageable= PageRequest.of(request.getOffset(), request.getLimit(),sort);
		return productRepository.findAll(spec, pageable);
	}

	public ResponseEntity<ResponseHttp> update(String id, ProductPutDto bookPutDto) {
		Optional<Product> optionalBook = productRepository.findById(id);
		if (optionalBook.isPresent()) {
			Product product = optionalBook.get();
			ProductPutDto bookDto = modelMapper.map(product, ProductPutDto.class);
			if(bookPutDto.toString().equals(bookDto.toString())){
				return new ResponseEntity<>(new ResponseHttp(305,"No hay cambios para el libro: "+ bookPutDto.getTitle()+"."),HttpStatus.OK);
			}
			if(bookPutDto.getIsxn()!=null){
				product.setIsxn(bookPutDto.getIsxn());
			}if(bookPutDto.getTitle()!=null){
				product.setTitle(bookPutDto.getTitle());
			} if(bookPutDto.getAuthor()!=null){
				product.setAuthor(bookPutDto.getAuthor());
			} if(bookPutDto.getEditorial()!=null){
				product.setEditorial(bookPutDto.getEditorial());
			} if(bookPutDto.getPublicationDate()!=null){
				product.setPublicationDate(bookPutDto.getPublicationDate());
			} if(bookPutDto.getUnits()!=null){
				product.setUnits(bookPutDto.getUnits());
			} if(bookPutDto.getCost()!=null){
				product.setCost(bookPutDto.getCost());
			} if(bookPutDto.getImage()!=null){
				product.setImage(bookPutDto.getImage());
			} if(bookPutDto.getCategoryId()!=null){
				Optional<Category> categoryOptional = categoryRepository.findById(bookPutDto.getCategoryId());
				if (categoryOptional.isEmpty())
					return new ResponseEntity<>(new ResponseHttp(204, "Categoria no encontrada"), HttpStatus.NO_CONTENT);
				product.setCategory(categoryOptional.get());
			}
			product.setAvailability();
			product.modify();
			productRepository.save(product);
			return new ResponseEntity<>(new ResponseHttp(200,"Libro: "+ bookPutDto.getTitle()+", actualizado con exito."), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseHttp(404,"Libro con el id: "+id+", no existe."), HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseHttp> delete(String id) {
		Optional<Product> book = productRepository.findById(id);
		if (book.isPresent()) {
			productRepository.deleteById(id);
			return new ResponseEntity<>(new ResponseHttp(200,"libro: "+book.get().getTitle()+", eliminado con exito"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseHttp(404,"Libro con el id: "+id+", no existe."), HttpStatus.NOT_FOUND);
		}
	}

	private String queryBuilder(BookRequestDto request) {
		StringBuilder query = new StringBuilder("SELECT b FROM Book b ");
		boolean whereAdded = false;

		if (request.getIsxn() != null || request.getTitle() != null || request.getEditorial() != null
				|| request.getAuthor() != null || request.getCategory() != null) {
			query.append("WHERE ");

			if (request.getIsxn() != null) {
				query.append("CAST(b.isxn AS string) LIKE CONCAT('%', :isxn, '%')");
				whereAdded = true;
			}
			if (request.getTitle() != null) {
				if (whereAdded) query.append(" OR ");
				query.append("LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))");
				whereAdded = true;
			}
			if (request.getEditorial() != null) {
				if (whereAdded) query.append(" OR ");
				query.append("LOWER(b.editorial) LIKE LOWER(CONCAT('%', :editorial, '%'))");
				whereAdded = true;
			}
			if (request.getAuthor() != null) {
				if (whereAdded) query.append(" OR ");
				query.append("LOWER(b.author) LIKE LOWER(CONCAT('%', :author, '%'))");
				whereAdded = true;
			}
			if (request.getCategory() != null) {
				if (whereAdded) query.append(" OR ");
				query.append("LOWER(b.category) LIKE LOWER(CONCAT('%', :category, '%'))");
			}
		}

		query.append(" ORDER BY b.modificationDate");
		return query.toString();
	}

}
