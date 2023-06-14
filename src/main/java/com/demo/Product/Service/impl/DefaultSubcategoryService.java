package com.demo.Product.Service.impl;

import com.demo.Product.Model.SubcategoryModel;
import com.demo.Product.Repository.SubcategoryRepository;
import com.demo.Product.Service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultSubcategoryService implements SubcategoryService {
    @Autowired
    private SubcategoryRepository subcategoryRepository;

    public List<SubcategoryModel> getAllSubcategories(){
        return getSubcategoryRepository().findAll();
    }
    public Optional<SubcategoryModel> getSubcategoryById(long id){
         return getSubcategoryRepository().findById(id);
    }

    public List<SubcategoryModel> createSubcategory(List<SubcategoryModel> subcategoryModel){
        return getSubcategoryRepository().saveAll(subcategoryModel);
    }

    public Optional<SubcategoryModel> updateSubcategory(long id){
        return getSubcategoryRepository().findById(id);
    }

    public ResponseEntity<Void> deleteSubcategoryById(long id){
        try {
            Optional<SubcategoryModel> subcategoryModel=getSubcategoryRepository().findById(id);
            if (subcategoryModel.isPresent()){
                getSubcategoryRepository().delete(subcategoryModel.get());
                return ResponseEntity.noContent().build();
            }else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    public SubcategoryRepository getSubcategoryRepository() {
        return subcategoryRepository;
    }

    public void setSubcategoryRepository(SubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
    }
}
