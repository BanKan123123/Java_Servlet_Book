package com.example.model;

import java.sql.Timestamp;

public class AuthorModel extends AbstractModel{

    private String name, slug;

    public AuthorModel() {}

    public AuthorModel(int id, Timestamp created_at, Timestamp updated_at, String name, String slug) {
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
