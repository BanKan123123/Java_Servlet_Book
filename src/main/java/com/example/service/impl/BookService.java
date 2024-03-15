package com.example.service.impl;

import com.example.dao.impl.BookDAO;
import com.example.model.BookModel;
import com.example.service.IBookService;
import com.example.utils.HttpUtil;
import com.example.utils.ResponseAPIUtils;
import com.example.wrapper.WrapperResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookService implements IBookService {
    private final BookDAO bookDAO = new BookDAO();
    private final ResponseAPIUtils<BookModel> responseAPIUtils = new ResponseAPIUtils<>();

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
    public BookModel findOneBookBySlug(String slug) {
        return bookDAO.findOneBookBySlug(slug);
    }

    @Override
    public BookModel save(BookModel bookModel) {
        bookDAO.addBook(bookModel);
        return findOneBookBySlug(bookModel.getSlug());
    }

    @Override
    public void delete(String slug) {
        bookDAO.deleteBook(slug);
    }

    @Override
    public BookModel update(BookModel bookModel, String slug) {
        bookDAO.updateBook(bookModel, slug);
        return findOneBookBySlug(bookModel.getSlug());
    }

    public void findData(String pathInfo, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        WrapperResponse<BookModel> wrapperResponse = new WrapperResponse<>();

        if (pathInfo == null || pathInfo.equals("/")) {
            ArrayList<BookModel> listBooks = (ArrayList<BookModel>) findAllBooks();
            responseAPIUtils.getDataSuccess(wrapperResponse, listBooks, resp);
        } else {
            String slug = pathInfo.substring(1);
            BookModel bookModel = findOneBookBySlug(slug);
            if (bookModel != null) {
                ArrayList<BookModel> books = new ArrayList<>();
                books.add(bookModel);
                responseAPIUtils.getDataSuccess(wrapperResponse, books, resp);
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
        WrapperResponse<BookModel> wrapperResponse = new WrapperResponse<>();
        BookModel bookModel = HttpUtil.of(req.getReader()).toModel(BookModel.class);

        if (bookModel.getTitle() == null || bookModel.getTitle().isEmpty() || bookModel.getSlug() == null || bookModel.getSlug().isEmpty() || bookModel.getDescription() == null || bookModel.getDescription().isEmpty() || bookModel.getCategories() == null || bookModel.getCategories().isEmpty() || bookModel.getQuantity() == 0) {
            responseAPIUtils.requiredDataAPI(wrapperResponse, resp);
        } else {
            BookModel findBook = findOneBookBySlug(bookModel.getSlug());
            if (findBook == null) {
                BookModel bookModel1 = save(bookModel);
                ArrayList<BookModel> books = new ArrayList<>();
                books.add(bookModel1);
                responseAPIUtils.insertSuccess(wrapperResponse, books, resp);
            } else {
                responseAPIUtils.duplicateDataAPI(wrapperResponse, resp);
            }
        }
        mapper.writeValue(resp.getOutputStream(), wrapperResponse);
    }

    public void update(String pathInfo, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        WrapperResponse<BookModel> wrapperResponse = new WrapperResponse<>();
        BookModel bookModel = HttpUtil.of(req.getReader()).toModel(BookModel.class);
        if (pathInfo != null && !pathInfo.isEmpty()) {
            String[] path = pathInfo.split("/");
            if (path.length == 3) {
                if (path[1].equals("update")) {
                    if (bookModel.getTitle() == null || bookModel.getTitle().isEmpty() || bookModel.getSlug() == null || bookModel.getSlug().isEmpty() || bookModel.getDescription() == null || bookModel.getDescription().isEmpty() || bookModel.getCategories() == null || bookModel.getCategories().isEmpty() || bookModel.getQuantity() == 0) {
                        responseAPIUtils.requiredDataAPI(wrapperResponse, resp);
                    } else {
                        ArrayList<BookModel> books = new ArrayList<>();
                        String slug = path[2];
                        if (findOneBookBySlug(slug) != null) {
                            if (findOneBookBySlug(bookModel.getSlug()) == null) {
                                BookModel bookModel1 = update(bookModel, slug);
                                books.add(bookModel1);
                                responseAPIUtils.updateSuccess(wrapperResponse, books, resp);
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

    public void delete(String pathInfo, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        WrapperResponse<BookModel> wrapperResponse = new WrapperResponse<>();
        if (pathInfo != null && !pathInfo.isEmpty()) {
            String[] path = pathInfo.split("/");
            if (path.length == 3) {
                if (path[1].equals("delete")) {
                    String slug = path[2];
                    if (findOneBookBySlug(slug) != null) {
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
