package com.example.model;

public class CategoryModel extends AbstractModel{

    private String name, slug;

    public CategoryModel() {
    }

    public CategoryModel(String name, String slug) {
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
