package com.demo.Product.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "category")
public class CategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<SubcategoryModel> subcategories;


    public CategoryModel() {

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

    public List<SubcategoryModel> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<SubcategoryModel> subcategories) {
        this.subcategories = subcategories;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
