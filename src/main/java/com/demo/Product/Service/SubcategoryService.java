package com.demo.Product.Service;

import com.demo.Product.Model.SubcategoryModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface SubcategoryService {

    public List<SubcategoryModel> createSubcategories(List<SubcategoryModel> subcategories);
    public Optional<SubcategoryModel> updateSubcategory(String code);
    public List<SubcategoryModel> getAllSubcategories();
    public Optional<SubcategoryModel> getSubcategoryByCode(String code);

    public ResponseEntity<Void> deleteSubcategoryByCode(String code);
}
