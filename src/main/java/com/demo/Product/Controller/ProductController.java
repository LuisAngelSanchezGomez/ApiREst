package com.demo.Product.Controller;

import com.demo.Product.Model.CategoryModel;
import com.demo.Product.Model.ProductModel;
import com.demo.Product.Model.SubcategoryModel;
import com.demo.Product.Repository.ProductRepository;
import com.demo.Product.Service.impl.DefaultCategoryService;
import com.demo.Product.Service.impl.DefaultProductService;
import com.demo.Product.Service.impl.DefaultSubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductController {

    @Autowired
    private DefaultProductService productService;
    @Autowired
    private DefaultCategoryService categoryService;
    @Autowired
    private DefaultSubcategoryService subcategoryService;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity createProducts(@RequestBody List<ProductModel> product) {
        try {
            List<ProductModel> createdProducts = getProductService().createProduct(product);
            return ResponseEntity.ok(createdProducts);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> getProduct(@PathVariable Long id) {
        ProductModel product = getProductService().getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}")
    public ProductModel updateProduct(@PathVariable Long id, @RequestBody ProductModel updatedProduct) {
        return getProductService().updateProduct(id,updatedProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        getProductService().deleteProduct(id);
    }


    @GetMapping
    public List<ProductModel> getProductsByCategoryAndSubcategory(@RequestParam(required = false) String subcategoryCode) {

        if (subcategoryCode != null) {
            return getProductService().getProductsBySubcategory(subcategoryCode);
        } else {
            return getProductService().getAllProducts();
        }
    }
    @GetMapping("/inventory-range")
    public List<ProductModel> getProductsByInventoryRange(@RequestParam int minInventory, @RequestParam int maxInventory) {
        return getProductService().getProductsByInventoryRange(minInventory, maxInventory);
    }



    public DefaultProductService getProductService() {
        return productService;
    }

    public void setProductService(DefaultProductService productService) {
        this.productService = productService;
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

}
