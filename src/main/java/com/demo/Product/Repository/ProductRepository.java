package com.demo.Product.Repository;

import com.demo.Product.Model.CategoryModel;
import com.demo.Product.Model.ProductModel;
import com.demo.Product.Model.SubcategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    /*List<ProductModel> findByCategoryCodeAndSubcategoryCode(String categoryCode, String subcategoryCode);
    List<ProductModel> findByCategoryCode(String categoryCode);*/
    List<ProductModel> findBySubcategoryCode(String subcategoryCode);
    List<ProductModel> findByInventoryBetween(int minInventory, int maxInventory);


}
