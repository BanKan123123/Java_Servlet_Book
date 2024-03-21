package com.example.controller.web;

import com.example.apihandler.GenericHandleAPI;
import com.example.model.AuthorModel;
import com.example.model.BookModel;
import com.example.model.CategoryModel;
import com.example.paramMapper.BookParamMapper;
import com.example.wrapper.WrapperResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/book/*", "/book"})
public class BookController extends HttpServlet {
    private final GenericHandleAPI<BookModel> genericHandleAPI = new GenericHandleAPI<>();
    private final GenericHandleAPI<AuthorModel> genericHandleAPIAuthor = new GenericHandleAPI<>();
    private final GenericHandleAPI<CategoryModel> genericHandleAPICategory = new GenericHandleAPI<>();
    private String urlAPIBook;
    private String urlAPIAuthor;
    private String urlAPICategory;

    @Override
    public void init() {
        urlAPIBook = "http://localhost:8080/demo2-1.0-SNAPSHOT/api-admin-books";
        urlAPIAuthor = "http://localhost:8080/demo2-1.0-SNAPSHOT/api-admin-author";
        urlAPICategory = "http://localhost:8080/demo2-1.0-SNAPSHOT/api-admin-category";
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String path = "/views/admin/book.jsp";
        WrapperResponse<BookModel> responseDataBook = genericHandleAPI.getMultipleAPIHandle(urlAPIBook, pathInfo);
        WrapperResponse<AuthorModel> responseDataAuthor = genericHandleAPIAuthor.getMultipleAPIHandle(urlAPIAuthor, pathInfo);
        WrapperResponse<CategoryModel> responseDataCategory = genericHandleAPICategory.getMultipleAPIHandle(urlAPICategory, pathInfo);

        req.setAttribute("responseBook", responseDataBook.getData());
        req.setAttribute("responseAuthor", responseDataAuthor.getData());
        req.setAttribute("responseCategory", responseDataCategory.getData());

        req.getRequestDispatcher(path).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        if (req.getParameter("action") != null) {
            String action = req.getParameter("action");
            if (action.equals("update")) {
                doPut(req, resp);
                resp.sendRedirect(req.getContextPath() + "/book");
            } else if (action.equals("delete")) {
                doDelete(req, resp);
                resp.sendRedirect(req.getContextPath() + "/book");
            }
        } else {
            String jsonBook = new BookParamMapper().mapperParam(req);
            genericHandleAPI.postAPIHandle(urlAPICategory, jsonBook, resp, mapper);
            resp.sendRedirect(req.getContextPath() + "/book");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String action = req.getParameter("action");
        String slug = req.getParameter("slug");
        String apiUrl = "http://localhost:8080/demo2-1.0-SNAPSHOT/api-admin-books/" + action + "/" + slug;
        String jsonBook = new BookParamMapper().mapperParam(req);

        genericHandleAPI.putAPIHandel(apiUrl, jsonBook, resp, mapper);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String action = req.getParameter("action");
        String slug = req.getParameter("slug");
        String apiUrl = "http://localhost:8080/demo2-1.0-SNAPSHOT/api-admin-books/" + action + "/" + slug;

        genericHandleAPI.deleteAPIHandel(apiUrl, resp, mapper);
    }
}
