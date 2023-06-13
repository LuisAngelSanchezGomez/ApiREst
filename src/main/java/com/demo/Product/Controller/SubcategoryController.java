package com.demo.Product.Controller;


import com.demo.Product.Model.CategoryModel;
import com.demo.Product.Model.SubcategoryModel;
import com.demo.Product.Repository.SubcategoryRepository;
import com.demo.Product.Service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subcategorias")
public class SubcategoryController {
    @Autowired
    private SubcategoryRepository subcategoryRepository;
    @Autowired
    private SubcategoryService subcategoryService;

    @GetMapping
    public List<SubcategoryModel> getAllSubcategories() {
        return getSubcategoryService().getSubcategories();
    }

    @GetMapping("/{id}")
    public Optional<SubcategoryModel> getSubcategoryById(@PathVariable Long id){
        return getSubcategoryService().getSubcategoryById(id);
    }

    @PostMapping
    public void createSubcategory(@RequestBody List<SubcategoryModel> subcategoryModel){
        getSubcategoryService().createSubcategory(subcategoryModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubcategoryModel> updateSubcategory(@PathVariable Long id, @RequestBody SubcategoryModel subcategoryData){
        try {
            Optional<SubcategoryModel> optionalSubcategoryModel= getSubcategoryService().updateSubcategory(id);
            if (optionalSubcategoryModel.isPresent()){
                SubcategoryModel subcategoryModel = optionalSubcategoryModel.get();
                subcategoryModel.setName(subcategoryData.getName());
                subcategoryModel.setCode(subcategoryData.getCode());
                getSubcategoryRepository().save(subcategoryModel);
                return ResponseEntity.ok(subcategoryModel);
            }else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteSubCategory(@PathVariable long id){
        return getSubcategoryService().deleteSubcategoryById(id);
    }

    public SubcategoryRepository getSubcategoryRepository() {
        return subcategoryRepository;
    }

    public void setSubcategoryRepository(SubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
    }

    public SubcategoryService getSubcategoryService() {
        return subcategoryService;
    }

    public void setSubcategoryService(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }
}
