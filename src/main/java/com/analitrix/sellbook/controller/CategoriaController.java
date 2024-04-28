package com.analitrix.sellbook.controller;

import com.analitrix.sellbook.entity.Categoria;
import com.analitrix.sellbook.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "http://localhost:4200/")

public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/all")
    public List<Categoria> allCategories(){
        return categoriaService.allCategories();
    }

}
