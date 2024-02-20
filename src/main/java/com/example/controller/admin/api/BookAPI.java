package com.example.controller.admin.api;

import com.example.model.BookModel;
import com.example.service.impl.BookService;
import com.example.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/api-admin-books/*"})
public class BookAPI extends HttpServlet {
    private BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        String pathInfo = req.getPathInfo();

        if (pathInfo != null && !pathInfo.isEmpty() && !pathInfo.equals("/")) {
            String idString = pathInfo.substring(1);
            int id = Integer.parseInt(idString);
            BookModel bookModel = bookService.findOneBookById(id);
            if (bookModel != null) {
                Gson gson = new Gson();
                String json = gson.toJson(bookModel);
                mapper.writeValue(resp.getOutputStream(), json);
            } else {
                resp.setStatus(404);
            }
        } else {
            ArrayList<BookModel> listBooks = (ArrayList<BookModel>) bookService.findAllBooks();
            Gson gson = new Gson();
            String json = gson.toJson(listBooks);
            mapper.writeValue(resp.getOutputStream(), json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8"); // Để khi mà thằng client nó có chuỗi tiếng việt
        resp.setContentType("application/json"); // Trả kết quả cho thằng client, và cụ thể là trả về json, thì thằng server định nghĩa một header để thằng Client hiểu.
        BookModel bookModel = HttpUtil.of(req.getReader()).toModel(BookModel.class);
        BookModel bookModel1 = bookService.save(bookModel);
        Gson gson = new Gson();
        String json = gson.toJson(bookModel1);
        mapper.writeValue(resp.getOutputStream(), json);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        BookModel bookModel = HttpUtil.of(req.getReader()).toModel(BookModel.class);
        String pathInfo = req.getPathInfo();
        if (pathInfo != null && !pathInfo.isEmpty()) {
            String idString = pathInfo.substring(1);
            int id = Integer.parseInt(idString);
            BookModel bookModelRes = bookService.update(bookModel, id);
            Gson gson = new Gson();
            String json = gson.toJson(bookModelRes);
            mapper.writeValue(resp.getOutputStream(), json);
        }
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        String pathInfo = req.getPathInfo();
        if (pathInfo != null && !pathInfo.isEmpty()) {
            String idString = pathInfo.substring(1);
            int id = Integer.parseInt(idString);
            BookModel bookModel = bookService.delete(id);
            Gson gson = new Gson();
            String json = gson.toJson(bookModel);
            mapper.writeValue(resp.getOutputStream(), json);
        }
    }
}
