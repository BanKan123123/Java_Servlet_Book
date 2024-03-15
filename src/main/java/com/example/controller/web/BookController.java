package com.example.controller.web;

import com.example.apihandler.GenericHandleAPI;
import com.example.model.AuthorModel;
import com.example.model.BookModel;
import com.example.paramMapper.BookParamMapper;
import com.example.service.impl.AuthorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = {"/book/*", "/book"})
public class BookController extends HttpServlet {
    private final GenericHandleAPI<BookModel> genericHandleAPI = new GenericHandleAPI<>();
    private String urlAPI;
    private final AuthorService authorService = new AuthorService();

    @Override
    public void init() {
        urlAPI = "http://localhost:8080/demo2-1.0-SNAPSHOT/api-admin-books";
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String path = "/views/admin/book.jsp";
        genericHandleAPI.getAPIHandle(urlAPI, pathInfo, path, resp, req);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        String jsonBook = new BookParamMapper().mapperParam(req);

        if (req.getParameter("_method").equals("PUT")) {
            doPut(req, resp);
        } else if (req.getParameter("_method").equals("DELETE")) {
            doDelete(req, resp);
        } else {
            genericHandleAPI.postAPIHandle(urlAPI, jsonBook, resp, mapper);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String pathInfo = req.getPathInfo();
        String apiUrl = "http://localhost:8080/demo2-1.0-SNAPSHOT/api-admin-books/" + pathInfo;
        String jsonBook = new BookParamMapper().mapperParam(req);
        genericHandleAPI.postAPIHandle(apiUrl, jsonBook, resp, mapper);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
