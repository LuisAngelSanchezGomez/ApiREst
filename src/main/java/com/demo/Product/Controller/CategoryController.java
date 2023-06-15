package com.demo.Product.Controller;

import com.demo.Product.Model.CategoryModel;
import com.demo.Product.Repository.CategoryRepository;
import com.demo.Product.Service.impl.DefaultCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private DefaultCategoryService categoryService;

    @GetMapping
    public List<CategoryModel> getAllCategories() {
        return getCategoryService().getAllCategories();
    }

    @GetMapping("/{categoryCode}")
    public Optional<CategoryModel> getCategoryByCode(@PathVariable String categoryCode){
        return getCategoryService().getCategoryByCode(categoryCode);
    }

    @PostMapping
    public ResponseEntity createCategory(@RequestBody List<CategoryModel> categories){
        try {
            getCategoryService().createCategory(categories);
            return ResponseEntity.ok(categories);
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/{categoryCode}")
    public ResponseEntity<CategoryModel> updateCategory(@PathVariable String categoryCode, @RequestBody CategoryModel categoryData){
        try {
            Optional<CategoryModel> optionalCategoryModel= getCategoryService().updateCategory(categoryCode);
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

    @DeleteMapping("/{categoryCode}")
    public  ResponseEntity<Void> deleteCategoryByCode(@PathVariable String categoryCode){
        return getCategoryService().deleteCategoryByCode(categoryCode);
    }
    @DeleteMapping()
    public  ResponseEntity<Void> deleteAllCategories(){
        return getCategoryService().deleteCategory();
    }

    public DefaultCategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(DefaultCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
