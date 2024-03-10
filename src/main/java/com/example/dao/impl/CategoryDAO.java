package com.example.dao.impl;

import com.example.dao.ICategoryDAO;
import com.example.mapper.CategoryMapper;
import com.example.model.AbstractModel;
import com.example.model.CategoryModel;

import java.util.List;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO {
    @Override
    public List<CategoryModel> findAllCategories() {
        String sql = "SELECT * FROM category ORDER BY name ASC";
        return query(sql, new CategoryMapper());
    }

    @Override
    public CategoryModel findOneCategory(String slug) {
        String sql = "SELECT * FROM category WHERE slug = ?";
        return query(sql, new CategoryMapper(), slug).get(0);
    }

    @Override
    public Long addCategory(CategoryModel categoryModel) {
        String sql = "INSERT INTO category (name, slug) VALUES (? , ?)";
        return insert(sql, categoryModel.getName(), categoryModel.getSlug());
    }

    @Override
    public void updateCategory(CategoryModel categoryModel, String slug) {
        String sql = "UPDATE category SET name = ?, slug = ? WHERE slug = ?";
        update(sql, categoryModel.getName(), categoryModel.getSlug(), slug);
    }

    @Override
    public void deleteCategory(String slug) {
        String sql = "DELETE FROM category WHERE slug = ?";
        update(sql, slug);
    }
}
