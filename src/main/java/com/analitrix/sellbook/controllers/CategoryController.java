package com.analitrix.sellbook.controllers;

import com.analitrix.sellbook.dtos.common.ResponseHttp;
import com.analitrix.sellbook.dtos.category.CategoryCreateDto;
import com.analitrix.sellbook.services.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Category")
@RestController
@RequestMapping("/api/v1/category")
@CrossOrigin(origins = "*")

public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<?> findAll(){
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable String id){
        return  categoryService.findOne(id);
    }

    @PostMapping("")
    public ResponseEntity<ResponseHttp> create(@RequestBody CategoryCreateDto categoryCreateDto){
        return categoryService.create(categoryCreateDto);
    }
}
