package com.demo.Product.Repository;

import com.demo.Product.Model.CategoryModel;
import com.demo.Product.Model.SubcategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubcategoryRepository extends JpaRepository<SubcategoryModel, Long> {
    Optional<SubcategoryModel> findByCode (String code);
}
