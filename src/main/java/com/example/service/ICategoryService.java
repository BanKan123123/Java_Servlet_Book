package com.example.service;

import com.example.model.AuthorModel;
import com.example.model.CategoryModel;

import java.util.List;

public interface ICategoryService {

    CategoryModel findOneCategoryBySlug(String slug);

    List<CategoryModel> findAllCategories();

    CategoryModel save(CategoryModel categoryModel);

    void delete(String slug);

    CategoryModel update(CategoryModel categoryModel, String slug);

}
