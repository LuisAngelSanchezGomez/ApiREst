package com.demo.Product.Service;

import com.demo.Product.Model.CategoryModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public List<CategoryModel> getCategories();

    public Optional<CategoryModel> getCategoryById(long id);

    public void createCategory(List<CategoryModel> categoryModel);

    public Optional<CategoryModel> updateCategory(long id);


    public ResponseEntity<Void> deleteCategoryById(long id);



}
