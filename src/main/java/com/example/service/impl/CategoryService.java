package com.example.service.impl;

import com.example.dao.impl.CategoryDAO;
import com.example.model.AuthorModel;
import com.example.model.CategoryModel;
import com.example.service.ICategoryService;
import com.example.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryService implements ICategoryService {
    private final CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    public CategoryModel findOneCategoryBySlug(String slug) {
        return categoryDAO.findOneCategory(slug);
    }

    @Override
    public List<CategoryModel> findAllCategories() {
        return categoryDAO.findAllCategories();
    }

    @Override
    public CategoryModel save(CategoryModel categoryModel) {
        categoryDAO.addCategory(categoryModel);
        return findOneCategoryBySlug(categoryModel.getSlug());
    }

    @Override
    public void delete(String slug) {
        categoryDAO.deleteCategory(slug);
    }

    @Override
    public CategoryModel update(CategoryModel categoryModel, String slug) {
        categoryDAO.updateCategory(categoryModel, slug);
        return categoryModel;
    }

    public void findData(String pathInfo, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        if (pathInfo == null || pathInfo.equals("/")) {
            ArrayList<CategoryModel> listCategories = (ArrayList<CategoryModel>) findAllCategories();
            mapper.writeValue(resp.getOutputStream(), listCategories);
        } else {
            String slug = pathInfo.substring(1);
            CategoryModel categoryModel = findOneCategoryBySlug(slug);
            if (categoryModel != null) {
                mapper.writeValue(resp.getOutputStream(), categoryModel);
            } else {
                resp.setStatus(404);
            }
        }
    }

    public void insertData(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8"); // Để khi mà thằng client nó có chuỗi tiếng việt
        resp.setContentType("application/json"); // Trả kết quả cho thằng client, và cụ thể là trả về json, thì thằng server định nghĩa một header để thằng Client hiểu.
        CategoryModel categoryModel = HttpUtil.of(req.getReader()).toModel(CategoryModel.class);
        CategoryModel categoryModel1 = save(categoryModel);
        mapper.writeValue(resp.getOutputStream(), categoryModel1);
    }

    public void updateData(String pathInfo, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        CategoryModel categoryModel = HttpUtil.of(req.getReader()).toModel(CategoryModel.class);
        if (pathInfo != null && !pathInfo.isEmpty()) {
            String[] path = pathInfo.split("/");
            if (path.length == 3) {
                if (path[1].equals("update")) {
                    String slug = path[2];
                    CategoryModel categoryModel1 = update(categoryModel, slug);
                    mapper.writeValue(resp.getOutputStream(), categoryModel1);
                }
            }
        }
    }

    public void deleteData(String pathInfo, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        if (pathInfo != null && !pathInfo.isEmpty()) {
            String[] path = pathInfo.split("/");
            if (path.length == 3) {
                if (path[1].equals("delete")) {
                    String slug = path[2];
                    delete(slug);
                }
            }
        }
    }

}
