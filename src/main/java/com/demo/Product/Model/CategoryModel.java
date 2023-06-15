package com.demo.Product.Model;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class CategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", unique = true, nullable = false)
    @NotNull
    private String code;

    @Column(name = "name", nullable = false)
    @NotNull
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubcategoryModel> subcategories = new ArrayList<>();

    public CategoryModel() {
    }

    public CategoryModel(String code, String name) {
        this.code = code;
        this.name = name;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<SubcategoryModel> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<SubcategoryModel> subcategories) {
        this.subcategories = subcategories;
    }

    public void addSubcategory(SubcategoryModel subcategory) {
        subcategory.setCategory(this);
        subcategories.add(subcategory);
    }

    public void removeSubcategory(SubcategoryModel subcategory) {
        subcategory.setCategory(null);
        subcategories.remove(subcategory);
    }
}