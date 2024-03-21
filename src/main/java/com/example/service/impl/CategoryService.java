package com.example.service.impl;

import com.example.dao.impl.CategoryDAO;
import com.example.model.CategoryModel;
import com.example.service.ICategoryService;
import com.example.utils.HttpUtil;
import com.example.utils.ResponseAPIUtils;
import com.example.wrapper.WrapperResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryService implements ICategoryService {
    private final CategoryDAO categoryDAO = new CategoryDAO();
    private final ResponseAPIUtils<CategoryModel> responseAPIUtils = new ResponseAPIUtils<>();

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
        if (categoryModel.getName().isEmpty() || categoryModel.getSlug().isEmpty()) {
            return null;
        } else {
            if (categoryDAO.addCategory(categoryModel) == null) {
                return null;
            } else {
                return findOneCategoryBySlug(categoryModel.getSlug());
            }
        }
    }

    @Override
    public void delete(String slug) {
        categoryDAO.deleteCategory(slug);
    }

    @Override
    public CategoryModel update(CategoryModel categoryModel, String slug) {
        categoryDAO.updateCategory(categoryModel, slug);
        return findOneCategoryBySlug(categoryModel.getSlug());
    }

    public void findData(String pathInfo, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        WrapperResponse<CategoryModel> wrapperResponse = new WrapperResponse<>();

        if (pathInfo == null || pathInfo.equals("/")) {
            ArrayList<CategoryModel> listCategories = (ArrayList<CategoryModel>) findAllCategories();
            responseAPIUtils.getDataSuccess(wrapperResponse, listCategories, resp);
        } else {
            String slug = pathInfo.substring(1);
            CategoryModel categoryModel = findOneCategoryBySlug(slug);
            ArrayList<CategoryModel> listCategories = new ArrayList<>();
            listCategories.add(categoryModel);
            if (categoryModel != null) {
                responseAPIUtils.getDataSuccess(wrapperResponse, listCategories, resp);
            } else {
                responseAPIUtils.notFoundAPI(wrapperResponse, resp);
            }
        }
        mapper.writeValue(resp.getOutputStream(), wrapperResponse);
    }

    public void insertData(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8"); // Để khi mà thằng client nó có chuỗi tiếng việt
        resp.setContentType("application/json"); // Trả kết quả cho thằng client, và cụ thể là trả về json, thì thằng server định nghĩa một header để thằng Client hiểu.
        WrapperResponse<CategoryModel> wrapperResponse = new WrapperResponse<>();

        CategoryModel categoryModel = HttpUtil.of(req.getReader()).toModel(CategoryModel.class);

        if (categoryModel.getName() == null || categoryModel.getName().isEmpty()) {
            responseAPIUtils.requiredDataAPI(wrapperResponse, resp);
        } else {
            CategoryModel findCategory = findOneCategoryBySlug(categoryModel.getSlug());
            if (findCategory == null) {
                CategoryModel categoryModel1 = save(categoryModel);
                ArrayList<CategoryModel> categoryModels = new ArrayList<>();
                categoryModels.add(categoryModel1);
                responseAPIUtils.insertSuccess(wrapperResponse, categoryModels, resp);
            } else {
                responseAPIUtils.duplicateDataAPI(wrapperResponse, resp);
            }
        }
        mapper.writeValue(resp.getOutputStream(), wrapperResponse);
    }

    public void updateData(String pathInfo, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        WrapperResponse<CategoryModel> wrapperResponse = new WrapperResponse<>();
        CategoryModel categoryModel = HttpUtil.of(req.getReader()).toModel(CategoryModel.class);
        if (pathInfo != null && !pathInfo.isEmpty()) {
            String[] path = pathInfo.split("/");
            if (path.length == 3) {
                if (path[1].equals("update") && !path[2].isEmpty()) {
                    if (categoryModel.getName() == null || categoryModel.getName().isEmpty()) {
                        responseAPIUtils.requiredDataAPI(wrapperResponse, resp);
                    } else {
                        ArrayList<CategoryModel> categoryModels = new ArrayList<>();
                        String slug = path[2];
                        if (findOneCategoryBySlug(slug) != null) {
                            if (findOneCategoryBySlug(categoryModel.getSlug()) == null) {
                                CategoryModel categoryModel1 = update(categoryModel, slug);
                                categoryModels.add(categoryModel1);
                                responseAPIUtils.updateSuccess(wrapperResponse, categoryModels, resp);
                            } else {
                                responseAPIUtils.duplicateDataAPI(wrapperResponse, resp);
                            }

                        } else {
                            responseAPIUtils.notFoundAPI(wrapperResponse, resp);
                        }
                    }
                } else {
                    responseAPIUtils.ServerError(wrapperResponse, resp);
                }
            } else {
                responseAPIUtils.ServerError(wrapperResponse, resp);
            }
        } else {
            responseAPIUtils.ServerError(wrapperResponse, resp);
        }
        mapper.writeValue(resp.getOutputStream(), wrapperResponse);
    }

    public void deleteData(String pathInfo, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        WrapperResponse<CategoryModel> wrapperResponse = new WrapperResponse<>();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        if (pathInfo != null && !pathInfo.isEmpty()) {
            String[] path = pathInfo.split("/");
            if (path.length == 3) {
                if (path[1].equals("delete")) {
                    String slug = path[2];
                    CategoryModel categoryModel1 = findOneCategoryBySlug(slug);
                    if (categoryModel1 != null) {
                        delete(slug);
                        responseAPIUtils.deleteSuccess(wrapperResponse, resp);
                    } else {
                        responseAPIUtils.notFoundAPI(wrapperResponse, resp);
                    }
                } else {
                    responseAPIUtils.ServerError(wrapperResponse, resp);
                }
            } else {
                responseAPIUtils.ServerError(wrapperResponse, resp);
            }
        } else {
            responseAPIUtils.ServerError(wrapperResponse, resp);
        }
        mapper.writeValue(resp.getOutputStream(), wrapperResponse);
    }
}
