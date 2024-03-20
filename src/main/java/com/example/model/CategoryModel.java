package com.example.model;

import java.sql.Timestamp;

public class CategoryModel extends AbstractModel{

    private String name, slug;

    public CategoryModel() {
    }

    public CategoryModel(String name, String slug) {
        this.name = name;
        this.slug = slug;
    }

    public CategoryModel(int id, Timestamp created_at, Timestamp updated_at, String name, String slug) {
        super(id, created_at, updated_at);
        this.name = name;
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
