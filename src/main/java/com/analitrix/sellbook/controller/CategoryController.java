package com.analitrix.sellbook.controller;

import com.analitrix.sellbook.entity.Category;
import com.analitrix.sellbook.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/sellbook/category")
@CrossOrigin(origins = "*")

public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/findAll")
    public List<Category> findAll(){
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id){
        return  categoryService.findById(id);
    }

}
