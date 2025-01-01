package com.analitrix.sellbook.controller;

import com.analitrix.sellbook.entity.Category;
import com.analitrix.sellbook.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@Tag(name="Category")
@RestController
@RequestMapping("/api/v1/category")
@CrossOrigin(origins = "*")

public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public List<Category> findAll(){
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable String id){
        return  categoryService.findById(id);
    }

}
