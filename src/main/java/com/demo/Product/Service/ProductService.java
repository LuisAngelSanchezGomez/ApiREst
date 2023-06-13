package com.demo.Product.Service;

import com.demo.Product.Model.CategoryModel;
import com.demo.Product.Model.ProductModel;
import com.demo.Product.Model.SubcategoryModel;
import com.demo.Product.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SubcategoryService subcategoryService;

    public List<ProductModel> showProducts(){
        return productRepository.findAll();
    }

    public void saveProduct(List<ProductModel> product){
         getProductRepository().saveAll(product);
    }

    public void deleteProductById(Long id){
        getProductRepository().deleteById(id);
    }

    public List<ProductModel> getProductByCategoryId(long id){
        return getProductRepository().findByCategoryId(id);
    }

    public List<ProductModel> getProductByInventoryRange(int min, int max){
        return getProductRepository().findByInventoryBetween(min,max);
    }

    public List<ProductModel> getProductsByCategoryAndSubcategory(long categoryId, long subcategoryId){
        Optional<CategoryModel> category = getCategoryService().getCategoryById(categoryId);
        Optional<SubcategoryModel> subcategoryModel = getSubcategoryService().getSubcategoryById(subcategoryId);

        return getProductRepository().findByCategoryAndSubcategoryId(category.get(),subcategoryModel.get());
    }
    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public SubcategoryService getSubcategoryService() {
        return subcategoryService;
    }

    public void setSubcategoryService(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }
}

