package com.example.dao;

import com.example.model.CategoryModel;

import java.util.List;

public interface ICategoryDAO extends GenericDAO<CategoryModel>{

    List<CategoryModel> findAllCategories();
    CategoryModel findOneCategory(String slug);

    List<CategoryModel> findCategoriesByQuery(String query);

    Long addCategory(CategoryModel categoryModel);

    void updateCategory(CategoryModel categoryModel, String slug);

    void deleteCategory(String slug);

}
