package com.example.dao.impl;

import com.example.dao.ICategoryDAO;
import com.example.mapper.CategoryMapper;
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
        if (query(sql, new CategoryMapper(), slug) == null || query(sql, new CategoryMapper(), slug).isEmpty()) {
            return null;
        }
        return query(sql, new CategoryMapper(), slug).get(0);
    }

    @Override
    public Long addCategory(CategoryModel categoryModel) {
        String sql = "INSERT INTO category (name, slug) VALUES (? , ?)";
        if (categoryModel.getName().isEmpty() || categoryModel.getSlug().isEmpty()) {
            return null;
        } else {
            CategoryModel categoryModel1 = findOneCategory(categoryModel.getSlug());
            if (categoryModel1 == null) {
                return insert(sql, categoryModel.getName(), categoryModel.getSlug());

            } else {
                return null;
            }
        }
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
