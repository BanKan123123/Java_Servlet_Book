package com.example.controller.web;

import com.example.apihandler.GenericHandleAPI;
import com.example.model.CategoryModel;
import com.example.paramMapper.CategoryParamMapper;
import com.example.wrapper.WrapperResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/categories")
public class CategoryController extends HttpServlet {
    private final GenericHandleAPI<CategoryModel> genericHandleAPIAuthor = new GenericHandleAPI<>();
    String urlAPICategory;

    @Override
    public void init() {
        urlAPICategory = "http://localhost:8080/demo2-1.0-SNAPSHOT/api-admin-category";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String path = "/views/admin/category.jsp";
        WrapperResponse<CategoryModel> responseCategory = null;

        if (req.getParameter("search") != null) {
            String pathSearch = urlAPICategory + "/search?query=" + req.getParameter("search");
            responseCategory = genericHandleAPIAuthor.getMultipleAPIHandle(pathSearch, pathInfo);
        } else {
            responseCategory = genericHandleAPIAuthor.getMultipleAPIHandle(urlAPICategory, pathInfo);
        }

        if (responseCategory != null) {
            req.setAttribute("responseCategory", responseCategory.getData());
        }
        req.getRequestDispatcher(path).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        if (req.getParameter("action") != null) {
            String action = req.getParameter("action");
            if (action.equals("update")) {
                doPut(req, resp);
                resp.sendRedirect(req.getContextPath() + "/categories");
            } else if (action.equals("delete")) {
                doDelete(req, resp);
                resp.sendRedirect(req.getContextPath() + "/categories");
            }
        } else {
            String jsonBook = new CategoryParamMapper().mapperParam(req);
            genericHandleAPIAuthor.postAPIHandle(urlAPICategory, jsonBook, resp, mapper);
            resp.sendRedirect(req.getContextPath() + "/categories");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String action = req.getParameter("action");
        String slug = req.getParameter("slug");
        String apiUrl = "http://localhost:8080/demo2-1.0-SNAPSHOT/api-admin-category/" + action + "/" + slug;
        String jsonBook = new CategoryParamMapper().mapperParam(req);
        genericHandleAPIAuthor.putAPIHandel(apiUrl, jsonBook, resp, mapper);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String action = req.getParameter("action");
        String slug = req.getParameter("slug");
        String apiUrl = "http://localhost:8080/demo2-1.0-SNAPSHOT/api-admin-category/" + action + "/" + slug;

        genericHandleAPIAuthor.deleteAPIHandel(apiUrl, resp, mapper);
    }
}
