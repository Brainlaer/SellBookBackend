package com.analitrix.sellbook.service;

import com.analitrix.sellbook.helpers.dto.ResponseHttp;
import com.analitrix.sellbook.model.core.Business;
import com.analitrix.sellbook.repository.BusinessRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BusinessService {

    private BusinessRepository businessRepository;

    public ResponseEntity<ResponseHttp> findById(UUID businessId){
        ResponseHttp responseHttp=new ResponseHttp();
        Business business = findBusinessById(businessId);

        if(business!=null){
            responseHttp.setCode(200);
            responseHttp.setDetail(business);
        }else{
            responseHttp.setCode(404);
            responseHttp.setDetail("BUSINESS NOT FOUND");
        }
        return ResponseEntity.ok(responseHttp);
    }

    public ResponseEntity<ResponseHttp> create(Business business){
        ResponseHttp responseHttp = new ResponseHttp();
        String error = verifyUnique(business);
        if(error!=null){
            responseHttp.setCode(400);
            responseHttp.setDetail(error);
        }
        businessRepository.create(business);
        responseHttp.setCode(204);
        responseHttp.setDetail("BUSINESS_CREATE");
        return ResponseEntity.ok(responseHttp);
    }

    private Business findBusinessById(UUID businessId){
        Optional<Business> business = businessRepository.findById(businessId);
        return business.orElse(null);
    }

    private String verifyUnique(Business business){
        if(businessRepository.existsBy("name",business.getName())){
            return "NAME_ALREADY_EXISTS";
        }else if(businessRepository.existsBy("documentNumber", business.getDocumentNumber())){
            return "DOCUMENT_NUMBER_ALREADY_EXISTS";
        }else{
            return null;
        }
    }
}
