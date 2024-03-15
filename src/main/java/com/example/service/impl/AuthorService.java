package com.example.service.impl;

import com.example.dao.impl.AuthorDAO;
import com.example.model.AuthorModel;
import com.example.service.IAuthorService;
import com.example.utils.HttpUtil;
import com.example.utils.ResponseAPIUtils;
import com.example.wrapper.WrapperResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthorService implements IAuthorService {
    private final AuthorDAO authorDAO = new AuthorDAO();
    private final ResponseAPIUtils<AuthorModel> responseAPIUtils = new ResponseAPIUtils<>();

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
        return findOneAuthorBySlug(slug);
    }

    public void findData(String pathInfo, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        WrapperResponse<AuthorModel> wrapperResponse = new WrapperResponse<>();

        if (pathInfo == null || pathInfo.equals("/")) {
            ArrayList<AuthorModel> listAuthors = (ArrayList<AuthorModel>) findAllAuthors();
            responseAPIUtils.getDataSuccess(wrapperResponse, listAuthors, resp);
        } else {
            String slug = pathInfo.substring(1);
            AuthorModel authorModel = findOneAuthorBySlug(slug);
            if (authorModel != null) {
                ArrayList<AuthorModel> authors = new ArrayList<>();
                authors.add(authorModel);
                responseAPIUtils.getDataSuccess(wrapperResponse, authors, resp);
            } else {
                responseAPIUtils.notFoundAPI(wrapperResponse, resp);
            }
        }
        mapper.writeValue(resp.getOutputStream(), wrapperResponse);
    }

    public void insertData(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        WrapperResponse<AuthorModel> wrapperResponse = new WrapperResponse<>();
        AuthorModel authorModel = HttpUtil.of(req.getReader()).toModel(AuthorModel.class);

        if (authorModel.getName().isEmpty() || authorModel.getName() == null || authorModel.getSlug() == null || authorModel.getSlug().isEmpty()) {
            responseAPIUtils.requiredDataAPI(wrapperResponse, resp);
        } else {
            AuthorModel findAuthor = findOneAuthorBySlug(authorModel.getSlug());
            if (findAuthor == null) {
                AuthorModel authorModel1 = save(authorModel);
                ArrayList<AuthorModel> authors = new ArrayList<>();
                authors.add(authorModel1);
                responseAPIUtils.insertSuccess(wrapperResponse, authors, resp);
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
        WrapperResponse<AuthorModel> wrapperResponse = new WrapperResponse<>();
        AuthorModel authorModel = HttpUtil.of(req.getReader()).toModel(AuthorModel.class);
        if (pathInfo != null && !pathInfo.isEmpty()) {
            String[] path = pathInfo.split("/");
            if (path.length == 3) {
                if (path[1].equals("update")) {
                    if (authorModel.getName().isEmpty() || authorModel.getName() == null || authorModel.getSlug() == null || authorModel.getSlug().isEmpty()) {
                        responseAPIUtils.requiredDataAPI(wrapperResponse, resp);
                    } else {
                        ArrayList<AuthorModel> authors = new ArrayList<>();
                        String slug = path[2];
                        if (findOneAuthorBySlug(slug) != null) {
                            AuthorModel authorModel1 = update(authorModel, slug);
                            authors.add(authorModel1);
                            responseAPIUtils.updateSuccess(wrapperResponse, authors, resp);
                        } else {
                            responseAPIUtils.duplicateDataAPI(wrapperResponse, resp);
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
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        WrapperResponse<AuthorModel> wrapperResponse = new WrapperResponse<>();
        if (pathInfo != null && !pathInfo.isEmpty()) {
            String[] path = pathInfo.split("/");
            if (path.length == 3) {
                if (path[1].equals("delete")) {
                    String slug = path[2];
                    if (findOneAuthorBySlug(slug) != null) {
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
