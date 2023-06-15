package com.demo.Product.Service.impl;

import com.demo.Product.Model.CategoryModel;
import com.demo.Product.Model.SubcategoryModel;
import com.demo.Product.Repository.CategoryRepository;
import com.demo.Product.Repository.SubcategoryRepository;
import com.demo.Product.Service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultSubcategoryService implements SubcategoryService {
    @Autowired
    private SubcategoryRepository subcategoryRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<SubcategoryModel> getAllSubcategories(){
        return getSubcategoryRepository().findAll();
    }
    public Optional<SubcategoryModel> getSubcategoryByCode(String code){
         return getSubcategoryRepository().findByCode(code);
    }

    @Override
    public List<SubcategoryModel> createSubcategories(List<SubcategoryModel> subcategories) {
        List<SubcategoryModel> createdSubcategories = new ArrayList<>();
        for (SubcategoryModel subcategory : subcategories){
            createdSubcategories.add(createSubcategory(subcategory));
        }
        return createdSubcategories;
    }
    public SubcategoryModel createSubcategory(SubcategoryModel subcategoryModel){
        CategoryModel categoryModel = getCategoryRepository().findByCode(subcategoryModel.getCategory().getCode())
                .orElseThrow(()-> new IllegalArgumentException("El Id de Categoria es invalido"));
        if (!categoryModel.getCode().equals(subcategoryModel.getCategory().getCode())){
            throw new IllegalArgumentException("Id de Categoria no coincide");
        }
        subcategoryModel.setCategory(categoryModel);
        SubcategoryModel createdSubcategory = getSubcategoryRepository().save(subcategoryModel);
        return createdSubcategory;
    }

    public Optional<SubcategoryModel> updateSubcategory(String code){
        return getSubcategoryRepository().findByCode(code);
    }

    public ResponseEntity<Void> deleteSubcategoryByCode(String code){
        try {
            Optional<SubcategoryModel> subcategoryModel=getSubcategoryRepository().findByCode(code);
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

    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
