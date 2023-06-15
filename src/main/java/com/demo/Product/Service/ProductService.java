package com.demo.Product.Service;

import com.demo.Product.Model.CategoryModel;
import com.demo.Product.Model.ProductModel;
import com.demo.Product.Model.SubcategoryModel;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<ProductModel> createProduct(List<ProductModel> product);

    public List<ProductModel> getAllProducts();

    public ProductModel getProductById(Long id);

    public ProductModel updateProduct(Long id, ProductModel updatedProduct);

    public void deleteProduct(Long id) ;

    public List<ProductModel> getProductsByCategoryAndSubcategory(String categoryCode, String subcategoryCode);

    public List<ProductModel> getProductsByCategory(String categoryCode) ;
    public List<ProductModel> getProductsBySubcategory(String subcategoryCode) ;

    public List<ProductModel> getProductsByInventoryRange(int minInventory, int maxInventory);
}
