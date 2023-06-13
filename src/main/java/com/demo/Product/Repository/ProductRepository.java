package com.demo.Product.Repository;

import com.demo.Product.Model.CategoryModel;
import com.demo.Product.Model.ProductModel;
import com.demo.Product.Model.SubcategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    List<ProductModel> findByCategoryId(Long categoryId);
    List<ProductModel> findByInventoryBetween(Integer min, Integer max);

    List<ProductModel> findByCategoryAndSubcategoryId(CategoryModel categoryId, SubcategoryModel subcategoryId);

}
