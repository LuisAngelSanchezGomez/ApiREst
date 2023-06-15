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
    public List<ProductModel> createProduct(List<ProductModel> products) {
        List<ProductModel> createdProducts = new ArrayList<>();
        for (ProductModel product : products){
            String categoryCode = product.getSubcategory().getCode();
            SubcategoryModel subcategoryModel = getSubcategoryRepository().findByCode(categoryCode).orElseThrow(()->new
                    RuntimeException("Categoria no encontrada por cdigo"+ categoryCode));
            product.setSubcategory(subcategoryModel);

            ProductModel createdProduct = getProductRepository().save(product);
            createdProducts.add(createdProduct);
        }
        return createdProducts;

    }

    @Override
    public List<ProductModel> getAllProducts() {
        return getProductRepository().findAll();
    }

    @Override
    public ProductModel getProductById(Long id) {
        return getProductRepository().findById(id).orElseThrow(()->new RuntimeException("Producto no enconrado con "+ id));
    }

    @Override
    public ProductModel updateProduct(Long id, ProductModel updatedProduct) {
        String categoryCode = updatedProduct.getSubcategory().getCode();
        SubcategoryModel subcategoryModel = getSubcategoryRepository().findByCode(categoryCode).orElseThrow(()->new
                RuntimeException("Categoria no encontrada por cdigo"+ categoryCode));
        ProductModel productModel = getProductRepository().findById(id).orElseThrow(()->new RuntimeException("Producto no encontrado con ese codigo"));
        productModel.setSku(updatedProduct.getSku());
        productModel.setName(updatedProduct.getName());
        productModel.setMaterialNumber(updatedProduct.getMaterialNumber());
        productModel.setSubcategory(subcategoryModel);
        productModel.setInventory(updatedProduct.getInventory());

        return getProductRepository().save(productModel);
    }

    @Override
    public void deleteProduct(Long id) {
        getProductRepository().deleteById(id);

    }

    @Override
    public List<ProductModel> getProductsByCategoryAndSubcategory(String categoryCode, String subcategoryCode) {
        return getProductRepository().findByCategoryCodeAndSubcategoryCode(categoryCode,subcategoryCode);
    }

    @Override
    public List<ProductModel> getProductsByCategory(String categoryCode) {
        return getProductRepository().findByCategoryCode(categoryCode);
    }

    @Override
    public List<ProductModel> getProductsBySubcategory(String subcategoryCode) {
        return getProductRepository().findBySubcategoryCode(subcategoryCode);
    }

    @Override
    public List<ProductModel> getProductsByInventoryRange(int minInventory, int maxInventory) {
        return getProductRepository().findByInventoryBetween(minInventory,maxInventory);
    }

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

