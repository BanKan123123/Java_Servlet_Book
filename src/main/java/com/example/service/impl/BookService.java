package com.example.service.impl;

import com.example.dao.impl.BookDAO;
import com.example.model.BookModel;
import com.example.service.IBookService;
import com.example.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookService implements IBookService {
    private final BookDAO bookDAO = new BookDAO();

    public BookService() {
    }

    @Override
    public BookModel findOneBookById(int id) {
        return bookDAO.findOneBookById(id);
    }

    @Override
    public List<BookModel> findAllBooks() {
        return bookDAO.findAllBooks();
    }

    @Override
    public BookModel save(BookModel bookModel) {
        bookDAO.addBook(bookModel);
        return bookModel;
    }

    @Override
    public void delete(int id) {
        bookDAO.deleteBook(id);
    }

    @Override
    public BookModel update(BookModel bookModel, int id) {
        bookDAO.updateBook(bookModel, id);
        return findOneBookById(id);
    }

    public void findData(String pathInfo, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        if (pathInfo == null || pathInfo.equals("/")) {
            ArrayList<BookModel> listBooks = (ArrayList<BookModel>) findAllBooks();
            mapper.writeValue(resp.getOutputStream(), listBooks);
        } else {
            String idString = pathInfo.substring(1);
            int id = Integer.parseInt(idString);
            BookModel bookModel = findOneBookById(id);
            if (bookModel != null) {
                mapper.writeValue(resp.getOutputStream(), bookModel);
            } else {
                resp.setStatus(404);
            }
        }
    }

    public void insertData(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8"); // Để khi mà thằng client nó có chuỗi tiếng việt
        resp.setContentType("application/json"); // Trả kết quả cho thằng client, và cụ thể là trả về json, thì thằng server định nghĩa một header để thằng Client hiểu.
        BookModel bookModel = HttpUtil.of(req.getReader()).toModel(BookModel.class);
        BookModel bookModel1 = save(bookModel);
        mapper.writeValue(resp.getOutputStream(), bookModel1);
    }

    public void update(String pathInfo, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        BookModel bookModel = HttpUtil.of(req.getReader()).toModel(BookModel.class);
        if (pathInfo != null && !pathInfo.isEmpty()) {
            String[] path = pathInfo.split("/");
            if (path.length == 3) {
                if (path[1].equals("update")) {
                    int id = Integer.parseInt(path[2]);
                    BookModel bookModelRes = update(bookModel, id);
                    mapper.writeValue(resp.getOutputStream(), bookModelRes);
                }
            }
        }
    }

    public void delete(String pathInfo, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        if (pathInfo != null && !pathInfo.isEmpty()) {
            String[] path = pathInfo.split("/");
            if (path.length == 3) {
                if (path[1].equals("delete")) {
                    int id = Integer.parseInt(path[2]);
                    delete(id);
                }
            }
        }
    }
}
