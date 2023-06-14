package com.demo.Product.Service;

import com.demo.Product.Model.SubcategoryModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface SubcategoryService {

    public List<SubcategoryModel> createSubcategory(List<SubcategoryModel> subcategoryModel);
    public Optional<SubcategoryModel> updateSubcategory(long id);
    public List<SubcategoryModel> getAllSubcategories();
    public Optional<SubcategoryModel> getSubcategoryById(long id);

    public ResponseEntity<Void> deleteSubcategoryById(long id);
}
