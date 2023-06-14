package com.demo.Product.Service;

import com.demo.Product.Model.CategoryModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public List<CategoryModel> createCategory(List<CategoryModel> categoryModel);
    public Optional<CategoryModel> updateCategory(long id);

    public ResponseEntity<Void> deleteCategoryById(long id);
    public ResponseEntity<Void> deleteCategory();


    public List<CategoryModel> getAllCategories();

    public CategoryModel getCategoryById(long id);







}
