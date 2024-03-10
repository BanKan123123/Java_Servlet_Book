package com.example.service.impl;

import com.example.dao.impl.AuthorDAO;
import com.example.model.AuthorModel;
import com.example.model.BookModel;
import com.example.service.IAuthorService;
import com.example.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthorChapter implements IAuthorService {
    private final AuthorDAO authorDAO = new AuthorDAO();

    @Override
    public AuthorModel findOneAuthorBySlug(String slug) {
        return authorDAO.findAuthorBySlug(slug);
    }

    @Override
    public List<AuthorModel> findAllAuthors() {
        return authorDAO.findAllAuthors();
    }

    @Override
    public AuthorModel save(AuthorModel authorModel) {
        authorDAO.addAuthor(authorModel);
        return findOneAuthorBySlug(authorModel.getSlug());
    }

    @Override
    public void delete(String slug) {
        authorDAO.deleteAuthor(slug);
    }

    @Override
    public AuthorModel update(AuthorModel authorModel, String slug) {
        authorDAO.updateAuthor(authorModel, slug);
        return findOneAuthorBySlug(slug); // Thay doi slug thi khong the tim thay.
    }

    public void findData(String pathInfo, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        if (pathInfo == null || pathInfo.equals("/")) {
            ArrayList<AuthorModel> listAuthors = (ArrayList<AuthorModel>) findAllAuthors();
            mapper.writeValue(resp.getOutputStream(), listAuthors);
        } else {
            String slug = pathInfo.substring(1);
            AuthorModel authorModel = findOneAuthorBySlug(slug);
            if (authorModel != null) {
                mapper.writeValue(resp.getOutputStream(), authorModel);
            } else {
                resp.setStatus(404);
            }
        }
    }

    public void insertData(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8"); // Để khi mà thằng client nó có chuỗi tiếng việt
        resp.setContentType("application/json"); // Trả kết quả cho thằng client, và cụ thể là trả về json, thì thằng server định nghĩa một header để thằng Client hiểu.
        AuthorModel authorModel = HttpUtil.of(req.getReader()).toModel(AuthorModel.class);
        AuthorModel authorModel1 = save(authorModel);
        mapper.writeValue(resp.getOutputStream(), authorModel1);
    }

    public void updateData(String pathInfo, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        AuthorModel authorModel = HttpUtil.of(req.getReader()).toModel(AuthorModel.class);
        if (pathInfo != null && !pathInfo.isEmpty()) {
            String[] path = pathInfo.split("/");
            if (path.length == 3) {
                if (path[1].equals("update")) {
                    String slug = path[2];
                    AuthorModel authorModel1 = update(authorModel, slug);
                    mapper.writeValue(resp.getOutputStream(), authorModel1);
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
