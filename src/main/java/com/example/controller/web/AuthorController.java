package com.example.controller.web;

import com.example.apihandler.GenericHandleAPI;
import com.example.model.AuthorModel;
import com.example.model.CategoryModel;
import com.example.paramMapper.AuthorParamMapper;
import com.example.paramMapper.CategoryParamMapper;
import com.example.wrapper.WrapperResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/author")
public class AuthorController extends HttpServlet {
    private final GenericHandleAPI<AuthorModel> genericHandleAPIAuthor = new GenericHandleAPI<>();
    String urlAPIAuthor;

    @Override
    public void init() {
        urlAPIAuthor = "http://localhost:8080/demo2-1.0-SNAPSHOT/api-admin-author";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String path = "/views/admin/author.jsp";
        WrapperResponse<AuthorModel> responseAuthor = genericHandleAPIAuthor.getMultipleAPIHandle(urlAPIAuthor, pathInfo);
        req.setAttribute("responseAuthor", responseAuthor.getData());
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
                resp.sendRedirect(req.getContextPath() + "/author");
            } else if (action.equals("delete")) {
                doDelete(req, resp);
                resp.sendRedirect(req.getContextPath() + "/author");
            }
        } else {
            String jsonAuthor = new AuthorParamMapper().mapperParam(req);
            genericHandleAPIAuthor.postAPIHandle(urlAPIAuthor, jsonAuthor, resp, mapper);
            resp.sendRedirect(req.getContextPath() + "/author");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String action = req.getParameter("action");
        String slug = req.getParameter("slug");
        String apiUrl = "http://localhost:8080/demo2-1.0-SNAPSHOT/api-admin-author/" + action + "/" + slug;
        String jsonAuthor = new AuthorParamMapper().mapperParam(req);
        genericHandleAPIAuthor.putAPIHandel(apiUrl, jsonAuthor, resp, mapper);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String action = req.getParameter("action");
        String slug = req.getParameter("slug");
        String apiUrl = "http://localhost:8080/demo2-1.0-SNAPSHOT/api-admin-author/" + action + "/" + slug;

        genericHandleAPIAuthor.deleteAPIHandel(apiUrl, resp, mapper);
    }
}
