package com.example.controller.web;

import com.example.apihandler.GenericHandleAPI;
import com.example.model.BookModel;
import com.example.model.ChapterModel;
import com.example.paramMapper.ChapterParamMapper;
import com.example.wrapper.WrapperResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/chapter")
public class ChapterController extends HttpServlet {

    private final GenericHandleAPI<ChapterModel> genericHandleAPI = new GenericHandleAPI<>();
    private final GenericHandleAPI<BookModel> genericHandleAPIBook = new GenericHandleAPI<>();

    private String urlAPIChapter;
    private String urlAPIBook;


    @Override
    public void init() {
        urlAPIChapter = "http://localhost:8080/demo2-1.0-SNAPSHOT/api-admin-chapters";
        urlAPIBook = "http://localhost:8080/demo2-1.0-SNAPSHOT/api-admin-books";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String path = "/views/admin/chapter.jsp";

        WrapperResponse<ChapterModel> wrapperResponse = genericHandleAPI.getMultipleAPIHandle(urlAPIChapter, pathInfo);
        WrapperResponse<BookModel> wrapperResponseBook = genericHandleAPIBook.getMultipleAPIHandle(urlAPIBook, pathInfo);

        req.setAttribute("responseChapter", wrapperResponse.getData());
        req.setAttribute("responseBook", wrapperResponseBook.getData());

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
                resp.sendRedirect(req.getContextPath() + "/chapter");
            } else if (action.equals("delete")) {
                doDelete(req, resp);
                resp.sendRedirect(req.getContextPath() + "/chapter");
            }
        } else {
            String jsonChapter = new ChapterParamMapper().mapperParam(req);
            genericHandleAPI.postAPIHandle(urlAPIChapter, jsonChapter, resp, mapper);
            resp.sendRedirect(req.getContextPath() + "/chapter");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String action = req.getParameter("action");
        String slug = req.getParameter("slug");
        String apiUrl = urlAPIChapter + "/" + action + "/" + slug;
        String jsonAuthor = new ChapterParamMapper().mapperParam(req);
        genericHandleAPI.putAPIHandel(apiUrl, jsonAuthor, resp, mapper);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String action = req.getParameter("action");
        String slug = req.getParameter("slug");
        String apiUrl = urlAPIChapter + "/" + action + "/" + slug;
        genericHandleAPI.deleteAPIHandel(apiUrl, resp, mapper);
    }


}
