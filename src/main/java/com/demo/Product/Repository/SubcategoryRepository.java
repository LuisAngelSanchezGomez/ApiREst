package com.demo.Product.Repository;

import com.demo.Product.Model.SubcategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoryRepository extends JpaRepository<SubcategoryModel, Long> {
}
