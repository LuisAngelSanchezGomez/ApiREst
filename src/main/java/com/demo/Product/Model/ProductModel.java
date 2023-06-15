package com.demo.Product.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sku", nullable = false)
    private String sku;
    @Column(name = "name", nullable = false)
    private String name;

    @Column(nullable = false)
    private String materialNumber;
    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private SubcategoryModel subcategory;

    @Column(name = "inventory", nullable = false)
    private int inventory;

    public ProductModel(Long id, String sku, String name, String materialNumber, SubcategoryModel subcategory, int inventory) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.materialNumber = materialNumber;
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
