package com.demo.Product.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sku;
    private String name;

    private String materialNumber;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryModel category;
    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private SubcategoryModel subcategory;

    private int inventory;

    public ProductModel(Long id, String sku, String name, String materialNumber, CategoryModel category, SubcategoryModel subcategory, int inventory) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.materialNumber = materialNumber;
        this.category = category;
        this.subcategory = subcategory;
        this.inventory = inventory;
    }

    public ProductModel() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public SubcategoryModel getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(SubcategoryModel subcategoryModel) {
        this.subcategory = subcategoryModel;
    }
}
