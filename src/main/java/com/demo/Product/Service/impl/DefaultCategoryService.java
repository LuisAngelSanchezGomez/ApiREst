package com.demo.Product.Service.impl;


import com.demo.Product.Model.CategoryModel;
import com.demo.Product.Repository.CategoryRepository;
import com.demo.Product.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultCategoryService implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryModel> getAllCategories(){
        return getCategoryRepository().findAll();
    }

    public Optional<CategoryModel> getCategoryByCode(String code){
        return getCategoryRepository().findByCode(code);
    }

    public List<CategoryModel> createCategory(List<CategoryModel> categoryModel){
        List<CategoryModel> createdCategories = new ArrayList<>();
        for (CategoryModel category : categoryModel){
            createdCategories.add(getCategoryRepository().save(category));
        }
        return createdCategories;
    }

    public Optional<CategoryModel> updateCategory(String code){
        return getCategoryRepository().findByCode(code);
    }

    public ResponseEntity<Void> deleteCategoryByCode(String code){
        try {
            Optional<CategoryModel> categoryModel=getCategoryRepository().findByCode(code);
            if (categoryModel.isPresent()){
                getCategoryRepository().delete(categoryModel.get());
                return ResponseEntity.noContent().build();
            }else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @Override
    public ResponseEntity<Void> deleteCategory() {
        getCategoryRepository().deleteAll();
        return ResponseEntity.noContent().build();
    }

    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
