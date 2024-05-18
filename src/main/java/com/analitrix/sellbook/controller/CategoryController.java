package com.analitrix.sellbook.controller;

import com.analitrix.sellbook.entity.Category;
import com.analitrix.sellbook.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:4200/")

public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/findAll")
    public List<Category> allCategories(){
        return categoryService.allCategories();
    }

}
