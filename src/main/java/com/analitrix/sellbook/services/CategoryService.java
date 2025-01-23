package com.analitrix.sellbook.services;


import com.analitrix.sellbook.dtos.common.ResponseHttp;
import com.analitrix.sellbook.dtos.category.CategoryCreateDto;
import com.analitrix.sellbook.models.Category;
import com.analitrix.sellbook.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    ModelMapper modelMapper=new ModelMapper();

    public ResponseEntity<ResponseHttp> findAll(){
        List<Category> categories = categoryRepository.findAll();
        if (categories.stream().count()==0){
            return new ResponseEntity<>(new ResponseHttp(204,"No hay categorias"),HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(new ResponseHttp(200, categories) ,HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseHttp> findOne(String id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            return new ResponseEntity<>(new ResponseHttp(200,category), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ResponseHttp(204,"No encontrado"), HttpStatus.NO_CONTENT);
        }

    }

    public ResponseEntity<ResponseHttp> create(CategoryCreateDto categoryCreateDto){
        Optional<Category> categoryOptional= categoryRepository.findByName(categoryCreateDto.getName());
        if(categoryOptional.isPresent())return new ResponseEntity<>(new ResponseHttp(409,"Categoria ya existe"), HttpStatus.CONFLICT);
        Category category = modelMapper.map(categoryCreateDto, Category.class);
        categoryRepository.save(category);
        return new ResponseEntity<>(new ResponseHttp(201,"Categoria creada"),HttpStatus.CREATED);
    }
}
