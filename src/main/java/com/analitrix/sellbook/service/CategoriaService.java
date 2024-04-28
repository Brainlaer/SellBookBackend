package com.analitrix.sellbook.service;

import com.analitrix.sellbook.entity.Categoria;
import com.analitrix.sellbook.repository.CategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepositorio categoriaRepositorio;

    public List<Categoria> allCategories(){
        return categoriaRepositorio.findAll();
    }

}
