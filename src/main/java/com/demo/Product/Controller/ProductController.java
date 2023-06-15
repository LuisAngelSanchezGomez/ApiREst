package com.demo.Product.Controller;

import com.demo.Product.Model.ProductModel;
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

    @GetMapping
    public List<ProductModel> getAllProducts() {
        return getProductService().showAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> getProduct(@PathVariable Long id) {
        ProductModel product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity createProducts(@RequestBody List<ProductModel> product) {
        try {
            List<ProductModel> createdProducts = getProductService().saveProduct(product);
            return ResponseEntity.ok(createdProducts);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
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



    /*@GetMapping("/categorias/{id}")
    public List<ProductModel> getProductsByCategory(@PathVariable Long id) {
        return getProductService().getProductByCategoryId(id);
    }*/

    @GetMapping(params = {"min","max"})
    public List<ProductModel> getProductByInventoryRange(@RequestParam("min") int min, @RequestParam("max") int max){
        return getProductService().getProductByInventoryRange(min,max);
    }

    /*@GetMapping("/categorias/{categoryId}/subcategorias/{subcategoryId}")
    public List<ProductModel> getProductsByCategoryAndSubcategory(@PathVariable Long categoryId, @PathVariable Long subcategoryId) {

        return getProductService().getProductsByCategoryAndSubcategory(categoryId,subcategoryId);
    }
*/
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
