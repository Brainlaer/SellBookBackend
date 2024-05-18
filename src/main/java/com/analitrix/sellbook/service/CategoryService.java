package com.analitrix.sellbook.service;


import com.analitrix.sellbook.entity.Category;
import com.analitrix.sellbook.repository.CategoryRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepositorio categoryRepositorio;

    public List<Category> allCategories(){
        return categoryRepositorio.findAll();
    }

}
