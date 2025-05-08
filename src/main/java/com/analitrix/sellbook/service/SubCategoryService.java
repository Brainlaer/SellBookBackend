package com.analitrix.sellbook.service;

import com.analitrix.sellbook.model.core.Business;
import com.analitrix.sellbook.model.core.SubCategory;
import com.analitrix.sellbook.repository.SubCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SubCategoryService {

    private SubCategoryRepository subCategoryRepository;

    public List<SubCategory> findAll(){

    }

    private boolean businessExists(UUID businessId){

    }

}
