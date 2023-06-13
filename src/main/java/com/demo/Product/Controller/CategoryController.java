package com.demo.Product.Controller;

import com.demo.Product.Model.CategoryModel;
import com.demo.Product.Repository.CategoryRepository;
import com.demo.Product.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryModel> getAllCategories() {
        return getCategoryService().getCategories();
    }

    @GetMapping("/{id}")
    public Optional<CategoryModel> getCategoryById(@PathVariable Long id){
        return getCategoryService().getCategoryById(id);
    }

    @PostMapping
    public void createCategory(@RequestBody List<CategoryModel> categories){
        getCategoryService().createCategory(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryModel> updateCategory(@PathVariable Long id, @RequestBody CategoryModel categoryData){
        try {
            Optional<CategoryModel> optionalCategoryModel= getCategoryService().updateCategory(id);
            if (optionalCategoryModel.isPresent()){
                CategoryModel categoryModel = optionalCategoryModel.get();
                categoryModel.setName(categoryData.getName());
                categoryModel.setCode(categoryData.getCode());
                getCategoryRepository().save(categoryModel);
                return ResponseEntity.ok(categoryModel);
            }else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteCategory(@PathVariable long id){
        return getCategoryService().deleteCategoryById(id);
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
