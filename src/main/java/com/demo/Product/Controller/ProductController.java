package com.demo.Product.Controller;

import com.demo.Product.Model.CategoryModel;
import com.demo.Product.Model.ProductModel;
import com.demo.Product.Repository.ProductRepository;
import com.demo.Product.Service.CategoryService;
import com.demo.Product.Service.ProductService;
import com.demo.Product.Service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SubcategoryService subcategoryService;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<ProductModel> getAllProducts() {
        return getProductService().showProducts();
    }

    @PostMapping
    public void createProduct(@RequestBody List<ProductModel> product) {
        getProductService().saveProduct(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable Long id, @RequestBody ProductModel productData) {
        Optional<ProductModel> optionalProductModel = getProductRepository().findById(id);
        if (optionalProductModel.isPresent()) {
            try {
                ProductModel product = optionalProductModel.get();
                product.setSku(productData.getSku());
                product.setName(productData.getName());
                product.setMaterialNumber(productData.getMaterialNumber());
                product.setInventory(productData.getInventory());
                product.setCategory(productData.getCategory());
                getProductRepository().save(product);
                return ResponseEntity.ok(product);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        getProductService().deleteProductById(id);
    }



    @GetMapping("/categorias/{id}")
    public List<ProductModel> getProductsByCategory(@PathVariable Long id) {
        return getProductService().getProductByCategoryId(id);
    }

    @GetMapping(params = {"min","max"})
    public List<ProductModel> getProductByInventoryRange(@RequestParam("min") int min, @RequestParam("max") int max){
        return getProductService().getProductByInventoryRange(min,max);
    }

    @GetMapping("/categorias/{categoryId}/subcategorias/{subcategoryId}")
    public List<ProductModel> getProductsByCategoryAndSubcategory(@PathVariable Long categoryId, @PathVariable Long subcategoryId) {

        return getProductService().getProductsByCategoryAndSubcategory(categoryId,subcategoryId);
    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

}
