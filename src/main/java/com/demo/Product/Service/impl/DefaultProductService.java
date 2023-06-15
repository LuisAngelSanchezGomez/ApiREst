package com.demo.Product.Service.impl;


import com.demo.Product.Model.ProductModel;
import com.demo.Product.Model.SubcategoryModel;
import com.demo.Product.Repository.ProductRepository;
import com.demo.Product.Repository.SubcategoryRepository;
import com.demo.Product.Service.ProductService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class DefaultProductService implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private DefaultCategoryService categoryService;
    @Autowired
    private DefaultSubcategoryService subcategoryService;
    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Override
    public ProductModel getProductById(Long id) {
        return getProductRepository().findById(id).orElse(null);
    }

    public List<ProductModel> showAllProducts(){
        return productRepository.findAll();
    }

    public List<ProductModel> saveProduct(List<ProductModel> products){
         List<ProductModel> createdProducts = new ArrayList<>();
         for (ProductModel product : products ){
             SubcategoryModel subcategoryModel = getSubcategoryRepository().findById(product.getSubcategory().getId())
                     .orElseThrow(()->new IllegalArgumentException("Id de subcategoria invalida"));
             product.setSubcategory(subcategoryModel);
             createdProducts.add(getProductRepository().save(product));
         }
         return createdProducts;
    }

    public void deleteProductById(Long id){
        getProductRepository().deleteById(id);
    }


    public List<ProductModel> getProductByInventoryRange(int min, int max){
        return getProductRepository().findByInventoryBetween(min,max);
    }

   /* public List<ProductModel> getProductsByCategoryAndSubcategory(long categoryId, long subcategoryId){
        Optional<CategoryModel> category = getCategoryService().getCategoryById(categoryId);
        Optional<SubcategoryModel> subcategoryModel = getSubcategoryService().getSubcategoryById(subcategoryId);

        return getProductRepository().findByCategoryAndSubcategoryId(category.get(),subcategoryModel.get());
    }*/
    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public DefaultCategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(DefaultCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public DefaultSubcategoryService getSubcategoryService() {
        return subcategoryService;
    }

    public void setSubcategoryService(DefaultSubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    public SubcategoryRepository getSubcategoryRepository() {
        return subcategoryRepository;
    }

    public void setSubcategoryRepository(SubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
    }
}

