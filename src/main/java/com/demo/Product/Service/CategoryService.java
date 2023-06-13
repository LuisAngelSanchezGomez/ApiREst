package com.demo.Product.Service;


import com.demo.Product.Model.CategoryModel;
import com.demo.Product.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryModel> getCategories(){
        return getCategoryRepository().findAll();
    }

    public Optional<CategoryModel> getCategoryById(long id){
        return  getCategoryRepository().findById(id);
    }

    public void createCategory(List<CategoryModel> categoryModel){
        getCategoryRepository().saveAll(categoryModel);
    }

    public Optional<CategoryModel> updateCategory(long id){
        return getCategoryRepository().findById(id);
    }

    public ResponseEntity<Void> deleteCategoryById(long id){
        try {
            Optional<CategoryModel> categoryModel=getCategoryRepository().findById(id);
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

    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
