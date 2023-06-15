package com.demo.Product.Service;

import com.demo.Product.Model.CategoryModel;
import com.demo.Product.Model.ProductModel;
import com.demo.Product.Model.SubcategoryModel;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public ProductModel getProductById(Long id);
    public List<ProductModel> showAllProducts();

    public List<ProductModel> saveProduct(List<ProductModel> products);

    public void deleteProductById(Long id);




    public List<ProductModel> getProductByInventoryRange(int min, int max);
    /*public List<ProductModel> getProductsByCategoryAndSubcategory(long categoryId, long subcategoryId);*/
}
